CREATE TABLE users (
                       userId SERIAL PRIMARY KEY,
                       name TEXT,
                       age TEXT,
                           owner_id TEXT,
                           organization_id TEXT,
                           created_at TIMESTAMP DEFAULT now()
);

ALTER TABLE users ENABLE ROW LEVEL SECURITY;


CREATE POLICY access_users_admin_user
  ON users
  FOR SELECT
                 TO admin_user
                 USING (
                 current_user = 'admin_user'
                 );




CREATE POLICY access_user
  ON users
  FOR SELECT
                 TO app_user
                 USING (
                    name = (current_setting('jwt.claims.context', true)::jsonb ->> 'user_id')
                 );
