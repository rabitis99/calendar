CREATE TABLE User (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    email VARCHAR(100) UNIQUE NOT NULL,
    name VARCHAR(100) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE Calendar (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    task TEXT NOT NULL,
    name VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    created_at DATE ,
    updated_at DATE ,
    user_id BIGINT,
    FOREIGN KEY (user_id) REFERENCES User(id) ON DELETE CASCADE
);