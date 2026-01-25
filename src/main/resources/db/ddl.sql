CREATE TABLE posts (
    id BIGSERIAL PRIMARY KEY,
    title TEXT,
    text TEXT,
    tags TEXT[] DEFAULT '{}',
    likesCount INTEGER DEFAULT 0 CHECK (likesCount >= 0),
    commentsCount INTEGER DEFAULT 0 CHECK (commentsCount >= 0)
);

CREATE TABLE comments (
    id BIGSERIAL PRIMARY KEY,
    text TEXT NOT NULL,
    postId BIGINT NOT NULL REFERENCES posts(id)
);



