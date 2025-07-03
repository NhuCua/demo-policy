
DROP POLICY IF EXISTS access_user ON users;

CREATE POLICY access_user
  ON users
  FOR SELECT
                 TO app_user
                 USING (
                    name = (current_setting('jwt.claims.context', true)::jsonb ->> 'sub')
                 );