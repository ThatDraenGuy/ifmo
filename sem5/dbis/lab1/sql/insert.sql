INSERT INTO celesital_body_type (celestial_body_type_name) 
VALUES 
  ('Star'), 
  ('Planet'), 
  ('Sattelite'), 
  ('Asteroid');


INSERT INTO hypothesis (hypothesis_name, description) 
VALUES 
  (
    '4 sattelites hypothesis', 'Согласно этой гипотезе Элара, Лиситея, Гималия и Леда являются частями одного распавшегося небесного тела'
  ), 
  (
    'Giant impact hypothesis', 'Согласно этой гипотезе, Луна была сформирована в результата столкновения гипотетической планеты Тейя с Землёй'
  );


INSERT INTO celestial_body (
  celestial_body_name, diameter, mass, 
  celestial_body_type_id
) 
VALUES 
  ('Sun', 1391400.0, 33295.0, 1), 
  ('Jupiter', 139822.0, 317.8, 2), 
  ('Elara', 80.0, 0.000000145, 3), 
  ('Lysithea', 42.0, 0.000000013, 3), 
  ('Himalia', 140.0, 0.000000703, 3), 
  ('Leda', 22.0, 0.00000000095, 3), 
  ('Earth', 12742.0, 1.0, 2), 
  ('Moon', 3474.0, 0.0123, 3);


INSERT INTO hypothesis_related_celestial_body (
  hypothesis_id, celestial_body_id
) 
VALUES 
  (1, 3), 
  (1, 4), 
  (1, 5), 
  (1, 6), 
  (2, 7), 
  (2, 8);


INSERT INTO hypothetical_celestial_body (
  hypothetical_celestial_body_name, 
  diameter_lower_bound, diameter_higher_bound, 
  mass_lower_bound, mass_higher_bound, 
  celestial_body_type_id, hypothesis_id
) 
VALUES 
  (NULL, NULL, 100.0, NULL, NULL, 4, 1), 
  (
    'Theia', 5000.0, 6500.0, NULL, NULL, 
    2, 2
  );


INSERT INTO orbit (
  orbiting_celestial_body_id, main_celestial_body_id, 
  eccentricity, semimajor_axis, inclanation, 
  accending_node_longtitude, periapsis_argument, 
  mean_anomaly
) 
VALUES 
  (
    2, 1, 0.0489, 5.2038, 6.09, 100.464, 
    273.867, 20.02
  ), 
  (
    3, 2, 0.22, 0.078, 30.66, 90.86, 191.199, 
    10.93
  ), 
  (
    4, 2, 0.148, 0.078, 26.29, 343.46, 94.8, 
    27.19
  ), 
  (
    5, 2, 0.154, 0.076, 29.9, 45.0, 21.6, 
    94.3
  ), 
  (
    6, 2, 0.165, 0.0748, 27.64, 190.18, 312.93, 
    137.03
  ), 
  (
    7, 1, 0.0167, 1.0, 7.155, 348.8, 114.2, 
    358.6
  ), 
  (
    8, 7, 0.055, 0.00257, 5.145, 125.08, 
    318.5, 135.27
  );
