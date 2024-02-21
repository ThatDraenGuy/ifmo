-- Для передачи аргументов в анонимный блок воспользуемся переменными конфигурации
\prompt 'Введите название таблицы: ' name_table
select set_config('s336765.table_name', :'name_table', false);

\prompt 'Введите название схемы: ' name_schema
select set_config('s336765.schema_name', :'name_schema', false);


DO $$DECLARE
    col RECORD;
    table_count int;
    table_name text = current_setting('s336765.table_name');
    schema_name text = current_setting('s336765.schema_name');
BEGIN
    SELECT COUNT(DISTINCT nspname)
    FROM pg_class class 
    JOIN pg_namespace space ON class.relnamespace = space.oid 
    WHERE relname = table_name AND space.nspname = schema_name
    INTO table_count;

    IF table_count < 1 THEN
        RAISE EXCEPTION 'Таблица "%" не найдена в схеме "%"!', table_name, schema_name;
    ELSE
        RAISE INFO ' ';
        RAISE INFO 'Таблица: %', table_name;
        RAISE INFO ' ';
        RAISE INFO 'No.  Имя столбца      Атрибуты';
        RAISE INFO '---  --------------   -------------------------------------------------';

        FOR col IN 
            SELECT class.relname, attr.attnum, attr.attname, 
            typ.typname, description.description, 
            idx.indexrelid::regclass AS idxname 
            FROM pg_class class
            JOIN pg_namespace space ON class.relnamespace = space.oid
            JOIN pg_attribute attr ON attr.attrelid = class.oid
            JOIN pg_type typ ON attr.atttypid = typ.oid
            LEFT JOIN pg_description description ON description.objoid = class.oid AND description.objsubid = attr.attnum
            LEFT JOIN pg_index idx ON class.oid = idx.indrelid AND attr.attnum = ANY(idx.indkey)
            WHERE class.relname = table_name AND attnum > 0 AND space.nspname = schema_name
            ORDER BY attnum
        LOOP
            RAISE INFO '% % Type    :  %', 
                RPAD(col.attnum::text, 5, ' '), 
                RPAD(col.attname, 16, ' '), 
                col.typname;

            RAISE INFO '% Commen  :  "%"', 
                RPAD('⠀', 22, ' '), 
                CASE WHEN col.description is null 
                    THEN '' 
                    ELSE col.description 
                    END;

            RAISE INFO '% Index   :  "%"', 
                RPAD('⠀', 22, ' '), 
                CASE WHEN col.idxname is null 
                    THEN '' 
                    ELSE col.idxname::text 
                    END;

            RAISE INFO ' ';
        END LOOP;
	END IF;
END$$;