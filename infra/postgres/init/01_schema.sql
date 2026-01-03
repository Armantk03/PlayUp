-- =========================
-- USERS
-- =========================
CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    puntos INT NOT NULL,
    nivel INT NOT NULL,
    registro TIMESTAMP NOT NULL,
    language VARCHAR(10) NOT NULL
);

-- =========================
-- AUTH
-- =========================
CREATE TABLE auth (
    user_id BIGINT PRIMARY KEY,
    password_hash VARCHAR(255) NOT NULL,
    role VARCHAR(20) NOT NULL,

    CONSTRAINT fk_auth_user
        FOREIGN KEY (user_id)
        REFERENCES users(id)
        ON DELETE CASCADE
);

-- =========================
-- EVENTS
-- =========================
CREATE TABLE events (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(150) NOT NULL,
    description TEXT,
    photo VARCHAR(255),
    points INT NOT NULL,
    finished BOOLEAN NOT NULL DEFAULT FALSE,
    creator_user_id BIGINT NOT NULL,

    CONSTRAINT fk_event_creator
        FOREIGN KEY (creator_user_id)
        REFERENCES users(id)
);

-- =========================
-- EVENT PARTICIPANTS
-- =========================
CREATE TABLE event_participants (
    id BIGSERIAL PRIMARY KEY,
    event_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,

    CONSTRAINT fk_participant_event
        FOREIGN KEY (event_id)
        REFERENCES events(id)
        ON DELETE CASCADE,

    CONSTRAINT fk_participant_user
        FOREIGN KEY (user_id)
        REFERENCES users(id)
        ON DELETE CASCADE,

    CONSTRAINT uk_event_user UNIQUE (event_id, user_id)
);
