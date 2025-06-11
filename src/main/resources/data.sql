-- Insère un cinéma test
INSERT INTO cinema (id_cinema, nom, adresse)
VALUES (1, 'Cinéma Test', '123 Rue Principale')
ON CONFLICT (id_cinema) DO NOTHING;

-- Insère deux salles liées au cinéma #1
INSERT INTO salle (
  id_salle, capacite, nbr_siege_std, nbr_siege_special, nbr_siege_pmr,
  photo, description, id_cinema
) VALUES
  (1, 120, 100, 15, 5, '', 'Salle principale', 1),
  (2,  80,  70,  5, 2, '', 'Salle secondaire', 1)
ON CONFLICT (id_salle) DO NOTHING;

-- Insère un utilisateur admin (mot de passe = admin123 encodé en bcrypt)
INSERT INTO utilisateur (id_user, mail, mdp, nom, prenom, role)
VALUES (
  1,
  'admin@example.com',
  '$2a$10$5/14JKY3ZV7x18vEMbrrEeE1RzDAkLDEl4YqIHCUxT8jf0/7UeN5q', -- bcrypt de "admin123"
  'Admin',
  'Super',
  'ADMIN'
)
ON CONFLICT (id_user) DO NOTHING;
