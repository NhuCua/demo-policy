CREATE TABLE documents (
                           id SERIAL PRIMARY KEY,
                           title TEXT,
                           content TEXT,
                           owner_id TEXT,
                           organization_id TEXT,
                           created_at TIMESTAMP DEFAULT now()
);

ALTER TABLE documents ENABLE ROW LEVEL SECURITY;

CREATE POLICY access_documents
  ON documents
  FOR SELECT
                 TO app_user
                 USING (
                 owner_id = current_setting('jwt.claims.user_id', true)
                 AND organization_id = current_setting('jwt.claims.organization_id', true)
                 );



CREATE POLICY access_documents_admin_user
  ON documents
  FOR SELECT
                 TO admin_user
                 USING (
                 current_user = 'admin_user'
                 );


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
                 name = current_setting('jwt.claims.user_id', true)
                 );
