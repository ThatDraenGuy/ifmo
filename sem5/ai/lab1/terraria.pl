% Боссы в игре
% boss(BossName)
boss(king_slime).
boss(eye_of_cthulhu).
boss(eater_of_worlds).
boss(brain_of_cthulhu).
boss(queen_bee).
boss(skeletron).
boss(deerclops).
boss(wall_of_flesh).

boss(queen_slime).
boss(the_twins).
boss(the_destroyer).
boss(skeletron_prime).
boss(plantera).
boss(golem).
boss(duke_fishron).
boss(empress_of_light).
boss(lunatic_cultist).
boss(moon_lord).

% Количество ХП босса
% health(Boss, BossHealth)
health(king_slime, 2800).
health(eye_of_cthulhu, 3640).
health(eater_of_worlds, 15120).
health(brain_of_cthulhu, 2125).
health(queen_bee, 4760).
health(skeletron, 8800).
health(deerclops, 11900).
health(wall_of_flesh, 11200).

health(queen_slime, 28800).
health(the_twins, 64500).
health(the_destroyer, 120000).
health(skeletron_prime, 42000).
health(plantera, 42000).
health(golem, 90000).
health(duke_fishron, 78000).
health(empress_of_light, 98000).
health(lunatic_cultist, 48000).
health(moon_lord, 217500).


% Уровень прогресса в игре
% progression_tier(TierName)
progression_tier(pre_boss).
progression_tier(molten).
progression_tier(shadow).
progression_tier(titanium).
progression_tier(chlorophyte).
progression_tier(ectoplasm).
progression_tier(beetle).
progression_tier(celestial).
progression_tier(luminite).

% Числовое представление уровня в игре
% progression_tier_value(TierName, TierValue)
progression_tier_value(pre_boss, 1).
progression_tier_value(molten, 2).
progression_tier_value(shadow, 3).
progression_tier_value(titanium, 4).
progression_tier_value(chlorophyte, 5).
progression_tier_value(ectoplasm, 6).
progression_tier_value(beetle, 7).
progression_tier_value(celestial, 8).
progression_tier_value(luminite, 9).


% Зависимость боссов от других, необходимых для доступа к ним
blocked_by(queen_slime, wall_of_flesh).
blocked_by(the_twins, wall_of_flesh).
blocked_by(the_destroyer, wall_of_flesh).
blocked_by(skeletron_prime, wall_of_flesh).
blocked_by(duke_fishron, wall_of_flesh).

blocked_by(plantera, the_twins).
blocked_by(plantera, the_destroyer).
blocked_by(plantera, skeletron_prime).

blocked_by(golem, plantera).
blocked_by(empress_of_light, plantera).

blocked_by(lunatic_cultist, golem).
blocked_by(lunatic_cultist, skeletron).

blocked_by(moon_lord, lunatic_cultist).


% Зависимость уровней прогресса от боссов, необходимых для их достижения
% blocked_by(ProgressionTier, BlockingBoss)
blocked_by(pre_boss, none).
blocked_by(molten, eater_of_worlds).
blocked_by(shadow, skeletron).
blocked_by(titanium, wall_of_flesh).

blocked_by(chlorophyte, the_twins).
blocked_by(chlorophyte, the_destroyer).
blocked_by(chlorophyte, skeletron_prime).

blocked_by(ectoplasm, plantera).
blocked_by(beetle, golem).
blocked_by(celestial, lunatic_cultist).
blocked_by(luminite, moon_lord).


% Рекомендуемые уровни прогресса для битвы с боссом
% recommended_tier(Boss, Tier)
recommended_tier(king_slime, pre_boss).
recommended_tier(eye_of_cthulhu, pre_boss).
recommended_tier(eater_of_worlds, pre_boss).
recommended_tier(brain_of_cthulhu, pre_boss).
recommended_tier(queen_bee, molten).
recommended_tier(skeletron, molten).
recommended_tier(deerclops, shadow).
recommended_tier(wall_of_flesh, shadow).

recommended_tier(queen_slime, titanium).
recommended_tier(the_twins, titanium).
recommended_tier(the_destroyer, titanium).
recommended_tier(skeletron_prime, titanium).
recommended_tier(plantera, chlorophyte).
recommended_tier(golem, ectoplasm).
recommended_tier(empress_of_light, ectoplasm).
recommended_tier(lunatic_cultist, beetle).
recommended_tier(duke_fishron, beetle).
recommended_tier(moon_lord, celestial).


%-------------------------------------------------------
% RULES
%-------------------------------------------------------


%-------------------------------------------------------
% Больше ли у босса А хп, чем у босса Б
has_more_health(BossA, BossB) :-
    boss(BossA), boss(BossB),
    health(BossA, HealthA), health(BossB, HealthB),
    HealthA > HealthB.


%-------------------------------------------------------
% Является Y зависимостью для X
required_for(X,Y) :- blocked_by(X,Y).
required_for(X,Y) :- blocked_by(X,Z), required_for(Z,Y).


% Список всех боссов, необходимых для доступа к конкретному боссу
required_bosses_list(Boss, RequiredBosses) :-
    boss(Boss),
    findall(Requirement, required_for(Boss, Requirement), RequirementList),    
    list_to_set(RequirementList, RequiredBosses).


%-------------------------------------------------------
% Необходимый уровень прогресса для доступа к боссу
required_tier(Boss, Tier) :-
    boss(Boss),
    progression_tier(Tier),
    (
        blocked_by(Boss, BlockingBoss),
        blocked_by(Tier, BlockingBoss);
        not(blocked_by(Boss, _)),
        Tier = pre_boss
    ).


% Доступен ли босс на этом уровне прогресса
available_at_tier(Boss, Tier) :-
    required_tier(Boss, RequiredTier),
    progression_tier_value(Tier, TierValue),
    progression_tier_value(RequiredTier, RequiredTierValue),
    TierValue >= RequiredTierValue.


% Список всех боссов, доступных на данном уровне прогресса
available_bosses_list(CurrentTier, AvailableBosses) :-
    findall(Boss, available_at_tier(Boss, CurrentTier), AvailableBosses).

% Список всех боссов, которые стали доступны на данном уровне прогресса
unlocked_by_tier_bosses_list(Tier, UnlockedBosses) :-
    findall(Boss, required_tier(Boss, Tier), UnlockedBosses).


%-------------------------------------------------------
% Является ли босс сложным для данного уровня прогресса
is_hard(Boss, CurrentTier) :-
    recommended_tier(Boss, RecommendedTier),
    progression_tier_value(CurrentTier, CurrentTierValue),
    progression_tier_value(RecommendedTier, RecommendedTierValue),
    CurrentTierValue < RecommendedTierValue.

% Является ли босс лёгким для данного уровня прогресса
is_easy(CurrentTier, Boss) :-
    recommended_tier(Boss, RecommendedTier),
    progression_tier_value(CurrentTier, CurrentTierValue),
    progression_tier_value(RecommendedTier, RecommendedTierValue),
    CurrentTierValue > RecommendedTierValue.


%--------------------------------------------
% Список всех боссов, рекомендованных для данного уровня прогресса
recommended_bosses_list(CurrentTier, Bosses) :-
    findall(Boss, recommended_tier(Boss, CurrentTier), Bosses).

% Является ли босс рекомендованным и ещё не побеждённым
is_recommended(CurrentTier, DefeatedBosses, Boss) :-
    recommended_tier(Boss, CurrentTier),
    not(member(Boss, DefeatedBosses)).


% Список всех рекомендованных и ещё не побеждённых боссов
recommended_bosses_list(CurrentTier, DefeatedBosses, RecommendedBosses) :-
    findall(Boss, is_recommended(CurrentTier, DefeatedBosses, Boss), RecommendedBosses).