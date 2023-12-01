INSERT INTO ability (ability_name) VALUES
	 ('СИЛА'),
	 ('ЛОВКОСТЬ'),
	 ('ТЕЛОСЛОЖЕНИЕ'),
	 ('ИНТЕЛЛЕКТ'),
	 ('МУДРОСТЬ'),
	 ('ХАРИЗМА');
INSERT INTO skill (skill_name,ability_id) VALUES
	 ('Атлетика',1),
	 ('Акробатика',2),
	 ('Ловкость рук',2),
	 ('Скрытность',2),
	 ('Анализ',4),
	 ('История',4),
	 ('Магия',4),
	 ('Природа',4),
	 ('Религия',4),
	 ('Внимательность',5),
	 ('Выживание',5),
	 ('Медицина',5),
	 ('Проницательность',5),
	 ('Уход за животными',5),
	 ('Выступление',6),
	 ('Запугивание',6),
	 ('Обман',6),
	 ('Убеждение',6);
INSERT INTO damage_type (damage_type_name) VALUES
	 ('Кислота'),
	 ('Дробящий'),
	 ('Холод'),
	 ('Огонь'),
	 ('Силовое поле'),
	 ('Электричество'),
	 ('Некротический'),
	 ('Колющий'),
	 ('Яд'),
	 ('Психический'),
	 ('Излучение'),
	 ('Режущий'),
	 ('Звук');
INSERT INTO "condition" (condition_name) VALUES
	 ('Бессознательный'),
	 ('Испуганный'),
	 ('Истощённый'),
	 ('Невидимый'),
	 ('Недееспособный'),
	 ('Оглохший'),
	 ('Окаменевший'),
	 ('Опутанный'),
	 ('Ослеплённый'),
	 ('Отравленный'),
	 ('Очарованный'),
	 ('Ошеломлённый'),
	 ('Парализованный'),
	 ('Сбитый с ног'),
	 ('Схваченный');
INSERT INTO creature_type (creature_type_name) VALUES
	 ('Абберация'),
	 ('Великан'),
	 ('Гуманоид'),
	 ('Дракон'),
	 ('Зверь'),
	 ('Исчадие'),
	 ('Конструкт'),
	 ('Монстр'),
	 ('Небожитель'),
	 ('Нежить'),
	 ('Растение'),
	 ('Слизь'),
	 ('Фея'),
	 ('Элементаль');
INSERT INTO dice_type (side_count) VALUES
	 (4),
	 (6),
	 (8),
	 (10),
	 (12),
	 (20);
	
	
	
INSERT INTO stat_block (entity_name,hit_points,hit_dice_type,hit_dice_count,armor_class,speed,"level",creature_type_id) VALUES
	 ('Гоблин пси-драчун',31,NULL,NULL,15,30,2,1),
	 ('Валл''акхад',36,8,4,18,30,4,3),
	 ('Билли Бобёр',34,NULL,NULL,34,40,3,7);
INSERT INTO player (player_name) VALUES
	 ('Олег');
INSERT INTO "character" (player_id,stat_block_id) VALUES
	 (1,2),
	 (NULL,1);
INSERT INTO current_stats (current_hit_points,character_id,temporary_hit_points,current_hit_dice_count,current_armor_class,current_speed) VALUES
	 (1,2,0,NULL,NULL,NULL),
	 (NULL,1,0,NULL,NULL,NULL);
	

INSERT INTO ability_scores (stat_block_id,ability_id,score) VALUES
	 (1,1,9),
	 (1,2,17),
	 (1,3,12),
	 (1,4,16),
	 (1,5,15),
	 (1,6,10),
	 (2,1,16),
	 (2,2,8),
	 (2,3,14),
	 (2,4,10);
INSERT INTO ability_scores (stat_block_id,ability_id,score) VALUES
	 (2,5,14),
	 (2,6,16),
	 (3,1,20),
	 (3,2,12),
	 (3,3,17),
	 (3,4,3),
	 (3,5,12),
	 (3,6,7);
INSERT INTO battle (round_number,current_character_index) VALUES
	 (1,1);
INSERT INTO current_conditions (current_stats_id,condition_id) VALUES
	 (1,2);
INSERT INTO damage_type_modifiers (stat_block_id,damage_type_id,modifier) VALUES
	 (1,10,0.5),
	 (3,10,0.0),
	 (3,9,0.0),
	 (2,10,0.5);
INSERT INTO initiative_entry (battle_id,character_id,initiative_roll) VALUES
	 (1,1,17),
	 (1,2,13);
INSERT INTO proficient_skills (stat_block_id,skill_id) VALUES
	 (1,4),
	 (2,5),
	 (2,9),
	 (2,11),
	 (2,12),
	 (2,13),
	 (2,18),
	 (3,10);
