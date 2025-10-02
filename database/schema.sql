-- Existing users table
CREATE TABLE IF NOT EXISTS users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- ===== Songs table =====
CREATE TABLE IF NOT EXISTS songs (
    id VARCHAR(10) PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    artist VARCHAR(255) NOT NULL,
    album VARCHAR(255),
    genre VARCHAR(50),
    year INT,
    url VARCHAR(500) NOT NULL,
    moods TEXT,
    weatherTags TEXT
);

-- Insert sample songs
INSERT INTO songs (id, title, artist, genre, url, moods, weatherTags) VALUES
('s1', 'Sunny Days', 'The Sunliners', 'pop', 'https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3', 'happy,energetic', 'clear,clouds'),
('s2', 'Rainy Window', 'Mellow Beats', 'lo-fi', 'https://www.soundhelix.com/examples/mp3/SoundHelix-Song-2.mp3', 'calm,sad,cozy', 'rain,clouds'),
('s3', 'Night Drive', 'Synth Trails', 'synth,electronic', 'https://www.soundhelix.com/examples/mp3/SoundHelix-Song-3.mp3', 'calm,romantic', 'clear,fog'),
('s4', 'Pump It Up', 'Energy Crew', 'edm', 'https://www.soundhelix.com/examples/mp3/SoundHelix-Song-4.mp3', 'energetic,happy', 'clear'),
('s5', 'Blue Minds', 'Soft Strings', 'classical', 'https://www.soundhelix.com/examples/mp3/SoundHelix-Song-5.mp3', 'sad,calm', 'rain,snow');
