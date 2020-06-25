-- Table
CREATE TABLE IF NOT EXISTS currency_exchange
  (  
     id               UUID PRIMARY KEY NOT NULL, 
     date             DATE NOT NULL, 
     currency_value   FLOAT NOT NULL,
     base_currency    VARCHAR(5) NOT NULL,
     target_currency  VARCHAR(5) NOT NULL 
  ) ;