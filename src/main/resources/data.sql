    -- Step 1: Delete all users if no admin with id = 1 exists
    DELETE FROM users WHERE NOT EXISTS (SELECT 1 FROM users WHERE id = 1 AND role = 'ADMIN');

    -- Step 2: Insert the admin user if no admin with id = 1 exists
    INSERT INTO users (id, first_name, last_name, email, password, dob, gender, role)
    SELECT 1, 'Admin', 'User', 'admin@example.com', '{bcrypt}$2a$10$6QvWlR/JjPS5EeNvdBzFMuy5/s.yT5J5g2Dk/SIXXNzK93hhUBzUm', NULL, 'Male', 'ADMIN'
    WHERE NOT EXISTS (SELECT 1 FROM users WHERE id = 1 AND role = 'ADMIN');
