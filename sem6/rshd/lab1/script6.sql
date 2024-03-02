\prompt 'Введите название таблицы: ' name_table
select set_config('s336765.table_name', :'name_table', false);

\prompt 'Введите название схемы: ' name_schema
select set_config('s336765.schema_name', :'name_schema', false);


DO $$DECLARE
    stmt varchar;
    cat_stmt varchar;
    categories varchar;
    table_name text = current_setting('s336765.table_name');
    schema_name text = current_setting('s336765.schema_name');
BEGIN
    DROP TABLE IF EXISTS temp_pivot;

    cat_stmt = '
        SELECT ''"Атрибут" text, ''
        || string_agg(DISTINCT attname::text, '' text, ''  ORDER BY attname::text)
        || '' text''
        FROM pg_class class
        JOIN pg_namespace space ON class.relnamespace = space.oid
        JOIN pg_attribute attr ON attr.attrelid = class.oid
        WHERE class.relname = '':table_name'' AND attnum > 0 AND space.nspname = '':schema_name'';
    ';
    cat_stmt = replace(cat_stmt, ':table_name', table_name);
    cat_stmt = replace(cat_stmt, ':schema_name', schema_name);
    EXECUTE cat_stmt INTO categories;

    stmt = 'CREATE TEMPORARY TABLE temp_pivot AS SELECT * FROM crosstab(''
    WITH attributes AS (
        SELECT
        class.oid,
        attr.attnum,
        attr.atttypid,
        attr.attname
        FROM pg_class class
        JOIN pg_namespace space ON class.relnamespace = space.oid
        JOIN pg_attribute attr ON attr.attrelid = class.oid
        JOIN pg_type typ ON attr.atttypid = typ.oid
        WHERE class.relname = '''':table_name'''' AND attnum > 0 AND space.nspname = '''':schema_name''''
        ORDER BY attname
    )
    (
        SELECT
        ''''Type'''' AS "Атрибут",
        attr.attname AS "Столбец",
        typ.typname AS "Значение"
        FROM attributes attr
        JOIN pg_type typ ON attr.atttypid = typ.oid
        ORDER BY attname
    ) UNION ALL (
        SELECT
        ''''Commen'''',
        attr.attname,
        description.description
        FROM attributes attr
        LEFT JOIN pg_description description ON description.objoid = attr.oid AND description.objsubid = attr.attnum
        ORDER BY attname
    ) UNION ALL (
        SELECT
        ''''Index'''',
        attr.attname,
        idx.indexrelid::regclass::varchar
        FROM attributes attr
        LEFT JOIN pg_index idx ON attr.oid = idx.indrelid AND attr.attnum = ANY(idx.indkey)
        ORDER BY attname
    )
    ORDER BY "Атрибут";
    '',
    ''
    SELECT
    attr.attname
    FROM pg_class class
    JOIN pg_namespace space ON class.relnamespace = space.oid
    JOIN pg_attribute attr ON attr.attrelid = class.oid
    WHERE class.relname = '''':table_name'''' AND attnum > 0 AND space.nspname = '''':schema_name''''
    ORDER BY attname
    '') AS ct(
        :categories
    );';

    stmt = replace(stmt, ':table_name', table_name);
    stmt = replace(stmt, ':schema_name', schema_name);
    stmt = replace(stmt, ':categories', categories);
    EXECUTE stmt;
END $$;
SELECT * FROM temp_pivot;