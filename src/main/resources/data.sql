-- Countries
INSERT INTO countries (name, code) VALUES
('Ukraine', 'UKR'),
('Russia', 'RUS'),
('Israel', 'ISR'),
('Palestine', 'PSE'),
('Syria', 'SYR'),
('Yemen', 'YEM');

-- Conflicts
INSERT INTO conflicts (name, start_date, status, description) VALUES
('War in Ukraine', '2022-02-24', 'ACTIVE', 'Military conflict between Russia and Ukraine'),
('Israel-Palestine Conflict', '2023-10-07', 'ACTIVE', 'Ongoing conflict between Israel and Hamas'),
('Syrian Civil War', '2011-03-15', 'FROZEN', 'Multi-sided civil war in Syria');

-- Conflict Countries
INSERT INTO conflict_countries (conflict_id, country_id) VALUES
(1, 1), (1, 2),  -- Ukraine-Russia
(2, 3), (2, 4),  -- Israel-Palestine
(3, 5);          -- Syria

-- Factions
INSERT INTO factions (name, conflict_id) VALUES
('Ukrainian Armed Forces', 1),
('Russian Armed Forces', 1),
('Israel Defense Forces', 2),
('Hamas', 2),
('Syrian Government', 3),
('Free Syrian Army', 3);

-- Events
INSERT INTO events (event_date, location, description, conflict_id) VALUES
('2022-02-24', 'Kyiv', 'Beginning of full-scale invasion', 1),
('2023-10-07', 'Gaza Strip', 'Hamas attack on Israel', 2),
('2011-03-15', 'Daraa', 'Start of Syrian uprising', 3);