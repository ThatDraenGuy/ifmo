CREATE TABLE IF NOT EXISTS celesital_body_type (
  celestial_body_type_id serial8 NOT NULL, 
  celestial_body_type_name varchar(100) NOT NULL, 
  CONSTRAINT celesital_body_type_pk PRIMARY KEY (celestial_body_type_id), 
  CONSTRAINT celesital_body_type_un UNIQUE (celestial_body_type_name), 
  CONSTRAINT name_check CHECK (
    (
      (celestial_body_type_name):: text <> '' :: text
    )
  )
);

CREATE TABLE IF NOT EXISTS hypothesis (
  hypothesis_id serial8 NOT NULL, 
  hypothesis_name varchar(100) NOT NULL, 
  description text NOT NULL, 
  CONSTRAINT hypothesis_pk PRIMARY KEY (hypothesis_id), 
  CONSTRAINT hypothesis_un UNIQUE (hypothesis_name), 
  CONSTRAINT name_check CHECK (
    (
      (hypothesis_name):: text <> '' :: text
    )
  )
);

CREATE TABLE IF NOT EXISTS celestial_body (
  celestial_body_id serial8 NOT NULL, 
  celestial_body_name varchar(100) NOT NULL, 
  diameter float8 NOT NULL, 
  mass float8 NOT NULL, 
  celestial_body_type_id int8 NOT NULL, 
  CONSTRAINT celestial_body_pk PRIMARY KEY (celestial_body_id), 
  CONSTRAINT celestial_body_un UNIQUE (celestial_body_name), 
  CONSTRAINT diameter_check CHECK (
    (
      diameter > (0):: double precision
    )
  ), 
  CONSTRAINT mass_check CHECK (
    (
      mass > (0):: double precision
    )
  ), 
  CONSTRAINT name_check CHECK (
    (
      (celestial_body_name):: text <> '' :: text
    )
  ), 
  CONSTRAINT celestial_body_fk FOREIGN KEY (celestial_body_type_id) REFERENCES celesital_body_type(celestial_body_type_id)
);

CREATE TABLE IF NOT EXISTS hypothesis_related_celestial_body (
  hypothesis_id int8 NOT NULL, 
  celestial_body_id int8 NOT NULL, 
  CONSTRAINT hypothesis_celestial_body_pk PRIMARY KEY (
    hypothesis_id, celestial_body_id
  ), 
  CONSTRAINT hypothesis_celestial_body_fk FOREIGN KEY (hypothesis_id) REFERENCES hypothesis(hypothesis_id), 
  CONSTRAINT hypothesis_celestial_body_fk_1 FOREIGN KEY (celestial_body_id) REFERENCES celestial_body(celestial_body_id)
);

CREATE TABLE IF NOT EXISTS hypothetical_celestial_body (
  hypothetical_celestial_body_id serial8 NOT NULL, 
  hypothetical_celestial_body_name varchar(100) NULL, 
  diameter_lower_bound float8 NULL, 
  diameter_higher_bound float8 NULL, 
  mass_lower_bound float8 NULL, 
  mass_higher_bound float8 NULL, 
  celestial_body_type_id int8 NOT NULL, 
  hypothesis_id int8 NOT NULL, 
  CONSTRAINT theoretical_celestial_body_pk PRIMARY KEY (hypothetical_celestial_body_id), 
  CONSTRAINT theoretical_celestial_body_fk FOREIGN KEY (celestial_body_type_id) REFERENCES celesital_body_type(celestial_body_type_id), 
  CONSTRAINT theoretical_celestial_body_fk_1 FOREIGN KEY (hypothesis_id) REFERENCES hypothesis(hypothesis_id)
);

CREATE TABLE IF NOT EXISTS orbit (
  orbiting_celestial_body_id int8 NOT NULL, 
  main_celestial_body_id int8 NOT NULL, 
  eccentricity float8 NOT NULL, 
  semimajor_axis float8 NOT NULL, 
  inclanation float8 NOT NULL, 
  accending_node_longtitude float8 NOT NULL, 
  periapsis_argument float8 NOT NULL, 
  mean_anomaly float8 NOT NULL, 
  CONSTRAINT accending_node_longtitude_check CHECK (
    (
      (
        accending_node_longtitude >= (0):: double precision
      ) 
      AND (
        accending_node_longtitude < (360):: double precision
      )
    )
  ), 
  CONSTRAINT eccentricity_check CHECK (
    (
      (
        eccentricity > (0):: double precision
      ) 
      AND (
        eccentricity < (1):: double precision
      )
    )
  ), 
  CONSTRAINT inclanation_check CHECK (
    (
      (
        inclanation >= (0):: double precision
      ) 
      AND (
        inclanation < (360):: double precision
      )
    )
  ), 
  CONSTRAINT mean_anomaly_check CHECK (
    (
      (
        mean_anomaly >= (0):: double precision
      ) 
      AND (
        mean_anomaly < (360):: double precision
      )
    )
  ), 
  CONSTRAINT orbit_pk PRIMARY KEY (orbiting_celestial_body_id), 
  CONSTRAINT periapsis_argument_check CHECK (
    (
      (
        periapsis_argument >= (0):: double precision
      ) 
      AND (
        periapsis_argument < (360):: double precision
      )
    )
  ), 
  CONSTRAINT orbit_fk FOREIGN KEY (orbiting_celestial_body_id) REFERENCES celestial_body(celestial_body_id), 
  CONSTRAINT orbit_fk_1 FOREIGN KEY (main_celestial_body_id) REFERENCES celestial_body(celestial_body_id)
);

CREATE TABLE IF NOT EXISTS human (
  human_id serial8 NOT NULL, 
  human_name varchar(100) NOT NULL, 
  human_surname varchar(100) NOT NULL, 
  human_age int8 NOT NULL, 
  human_gender bool NOT NULL, 
  CONSTRAINT human_pk PRIMARY KEY (human_id)
);

CREATE TABLE IF NOT EXISTS discovery (
  celestial_body_id int8 NOT NULL, 
  discovery_date date NOT NULL, 
  discovered_by_id int8 NOT NULL, 
  CONSTRAINT discovery_pk PRIMARY KEY (celestial_body_id), 
  CONSTRAINT discovery_fk_1 FOREIGN KEY (celestial_body_id) REFERENCES celestial_body(celestial_body_id), 
  CONSTRAINT discovery_fk_2 FOREIGN KEY (discovered_by_id) REFERENCES human(human_id)
);
