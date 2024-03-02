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
    WHERE class.relname = 'Н_ЛЮДИ' AND attnum > 0 AND space.nspname = 'public'
    ORDER BY attname
)
(
    SELECT
    'Type' AS "Атрибут",
    attr.attname AS "Столбец",
    typ.typname AS "Значение"
    FROM attributes attr
    JOIN pg_type typ ON attr.atttypid = typ.oid
    ORDER BY attname
) UNION ALL (
    SELECT
    'Commen',
    attr.attname,
    description.description
    FROM attributes attr
    LEFT JOIN pg_description description ON description.objoid = attr.oid AND description.objsubid = attr.attnum
    ORDER BY attname
) UNION ALL (
    SELECT
    'Index',
    attr.attname,
    idx.indexrelid::regclass::varchar
    FROM attributes attr
    LEFT JOIN pg_index idx ON attr.oid = idx.indrelid AND attr.attnum = ANY(idx.indkey)
    ORDER BY attname
);

-- (
--     SELECT
--     'Type' AS "Атрибут",
--     attr.attname AS "Столбец",
--     typ.typname AS "Значение"
--     FROM pg_class class
--     JOIN pg_namespace space ON class.relnamespace = space.oid
--     JOIN pg_attribute attr ON attr.attrelid = class.oid
--     JOIN pg_type typ ON attr.atttypid = typ.oid
--     WHERE class.relname = 'Н_ЛЮДИ' AND attnum > 0 AND space.nspname = 'public'
--     ORDER BY attname
-- ) UNION ALL (
--     SELECT
--     'Commen',
--     attr.attname,
--     description.description
--     FROM pg_class class
--     JOIN pg_namespace space ON class.relnamespace = space.oid
--     JOIN pg_attribute attr ON attr.attrelid = class.oid
--     LEFT JOIN pg_description description ON description.objoid = class.oid AND description.objsubid = attr.attnum
--     WHERE class.relname = 'Н_ЛЮДИ' AND attnum > 0 AND space.nspname = 'public'
--     ORDER BY attname
-- ) UNION ALL (
--     SELECT
--     'Index',
--     attr.attname,
--     idx.indexrelid::regclass::varchar
--     FROM pg_class class
--     JOIN pg_namespace space ON class.relnamespace = space.oid
--     JOIN pg_attribute attr ON attr.attrelid = class.oid
--     LEFT JOIN pg_index idx ON class.oid = idx.indrelid AND attr.attnum = ANY(idx.indkey)
--     WHERE class.relname = 'Н_ЛЮДИ' AND attnum > 0 AND space.nspname = 'public'
--     ORDER BY attname
-- );


-- SELECT * FROM crosstab('
-- (
--     SELECT
--     ''Type'' AS "Атрибут",
--     attr.attname AS "Столбец",
--     typ.typname AS "Значение"
--     FROM pg_class class
--     JOIN pg_namespace space ON class.relnamespace = space.oid
--     JOIN pg_attribute attr ON attr.attrelid = class.oid
--     JOIN pg_type typ ON attr.atttypid = typ.oid
--     WHERE class.relname = ''Н_ЛЮДИ'' AND attnum > 0 AND space.nspname = ''public''
--     ORDER BY attname
-- ) UNION ALL (
--     SELECT
--     ''Commen'',
--     attr.attname,
--     description.description
--     FROM pg_class class
--     JOIN pg_namespace space ON class.relnamespace = space.oid
--     JOIN pg_attribute attr ON attr.attrelid = class.oid
--     LEFT JOIN pg_description description ON description.objoid = class.oid AND description.objsubid = attr.attnum
--     WHERE class.relname = ''Н_ЛЮДИ'' AND attnum > 0 AND space.nspname = ''public''
--     ORDER BY attname
-- ) UNION ALL (
--     SELECT
--     ''Index'',
--     attr.attname,
--     idx.indexrelid::regclass::varchar
--     FROM pg_class class
--     JOIN pg_namespace space ON class.relnamespace = space.oid
--     JOIN pg_attribute attr ON attr.attrelid = class.oid
--     LEFT JOIN pg_index idx ON class.oid = idx.indrelid AND attr.attnum = ANY(idx.indkey)
--     WHERE class.relname = ''Н_ЛЮДИ'' AND attnum > 0 AND space.nspname = ''public''
--     ORDER BY attname
-- )
-- ORDER BY "Атрибут";
-- ',
-- '
-- SELECT
-- attr.attname
-- FROM pg_class class
-- JOIN pg_namespace space ON class.relnamespace = space.oid
-- JOIN pg_attribute attr ON attr.attrelid = class.oid
-- WHERE class.relname = ''Н_ЛЮДИ'' AND attnum > 0 AND space.nspname = ''public''
-- ORDER BY attname
-- ') AS ct(
--     "Атрибут" text,
--     "ДАТА_РОЖДЕНИЯ"   text,
--     "ДАТА_СМЕРТИ"     text,
--     "ИД"              text,
--     "ИМЯ"             text,
--     "ИНН"             text,
--     "ИНОСТРАН"        text,
--     "КОГДА_ИЗМЕНИЛ"   text,
--     "КОГДА_СОЗДАЛ"    text,
--     "КТО_ИЗМЕНИЛ"     text,
--     "КТО_СОЗДАЛ"      text,
--     "МЕСТО_РОЖДЕНИЯ"  text,
--     "ОТЧЕСТВО"        text,
--     "ПИН"             text,
--     "ПОЛ"             text,
--     "ФАМИЛИЯ"         text,
--     "ФИО"             text
-- );