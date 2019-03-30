DROP TABLE IF EXISTS platform_count;

CREATE TABLE platform_count (
  id          INTEGER PRIMARY KEY,
  short_url VARCHAR(64) NOT NULL,
  mobile   VARCHAR(64),
  browser VARCHAR(64));

// TODO insert Data