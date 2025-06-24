CREATE TABLE users (
                       userId SERIAL PRIMARY KEY,
                       name TEXT,
                       age TEXT,
                           owner_id TEXT,
                           organization_id TEXT,
                           created_at TIMESTAMP DEFAULT now()
);

ALTER TABLE users ENABLE ROW LEVEL SECURITY;

CREATE POLICY access_users
  ON users
  FOR SELECT
                 TO app_user
                 USING (
                    true
                 );
