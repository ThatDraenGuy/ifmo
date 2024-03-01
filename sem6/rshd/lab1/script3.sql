-- Для передачи аргументов в анонимный блок воспользуемся переменными конфигурации
BEGIN;

\prompt 'Введите название таблицы: ' name_table
select set_config('s336765.table_name', :'name_table', true);

\prompt 'Введите название схемы: ' name_schema
select set_config('s336765.schema_name', :'name_schema', true);


DO $$DECLARE
    _cursor CONSTANT refcursor := '_cursor';
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
        OPEN _cursor FOR 
        
        SELECT * FROM crosstab (
            'SELECT
            typ.typname,
            attr.attnotnull,
            attr.attname AS "Имя столбца"
            FROM pg_class class
            JOIN pg_namespace space ON class.relnamespace = space.oid
            JOIN pg_attribute attr ON attr.attrelid = class.oid
            JOIN pg_type typ ON attr.atttypid = typ.oid
            WHERE class.relname = ''Н_ЛЮДИ'' AND attnum > 0 AND space.nspname = ''public''
            ORDER BY attnum'
        ) as ct("Type" text, "f" text, "t" text);

        SELECT
            attr.attnum AS "No.",
            attr.attname AS "Имя столбца",
            typ.typname,
            description.description,
            idx.indexrelid::regclass
            FROM pg_class class
            JOIN pg_namespace space ON class.relnamespace = space.oid
            JOIN pg_attribute attr ON attr.attrelid = class.oid
            JOIN pg_type typ ON attr.atttypid = typ.oid
            LEFT JOIN pg_description description ON description.objoid = class.oid AND description.objsubid = attr.attnum
            LEFT JOIN pg_index idx ON class.oid = idx.indrelid AND attr.attnum = ANY(idx.indkey)
            WHERE class.relname = table_name AND attnum > 0 AND space.nspname = schema_name
            ORDER BY attnum;
	END IF;
END$$;

FETCH ALL FROM _cursor;

COMMIT;