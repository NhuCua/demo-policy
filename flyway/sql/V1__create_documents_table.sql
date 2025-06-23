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
