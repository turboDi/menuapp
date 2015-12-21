CREATE TABLE dish (
    id bigint NOT NULL,
    name character varying(255) NOT NULL,
    price numeric(10,2),
    menu_id bigint NOT NULL
);

CREATE SEQUENCE dish_id_seq;

CREATE TABLE menu (
    id bigint NOT NULL,
    date date,
    restaurant_id bigint NOT NULL
);

CREATE SEQUENCE menu_id_seq;

CREATE TABLE restaurant (
    id bigint NOT NULL,
    deleted boolean NOT NULL,
    name character varying(255) NOT NULL
);

CREATE SEQUENCE restaurant_id_seq;

CREATE TABLE users (
    id bigint NOT NULL,
    password character varying(255) NOT NULL,
    role character varying(255) NOT NULL,
    username character varying(255) NOT NULL,
    vote_id bigint
);

CREATE SEQUENCE users_id_seq;

CREATE TABLE vote (
  id bigint NOT NULL,
  restaurant_id bigint NOT NULL
);

CREATE SEQUENCE vote_id_seq;

ALTER TABLE dish ALTER COLUMN id SET DEFAULT nextval('dish_id_seq');

ALTER TABLE menu ALTER COLUMN id SET DEFAULT nextval('menu_id_seq');

ALTER TABLE restaurant ALTER COLUMN id SET DEFAULT nextval('restaurant_id_seq');

ALTER TABLE users ALTER COLUMN id SET DEFAULT nextval('users_id_seq');

ALTER TABLE vote ALTER COLUMN id SET DEFAULT nextval('vote_id_seq');

ALTER TABLE dish ADD CONSTRAINT dish_pkey PRIMARY KEY (id);

ALTER TABLE menu ADD CONSTRAINT menu_pkey PRIMARY KEY (id);

ALTER TABLE restaurant ADD CONSTRAINT restaurant_pkey PRIMARY KEY (id);

ALTER TABLE users ADD CONSTRAINT uk_username UNIQUE (username);

ALTER TABLE users ADD CONSTRAINT users_pkey PRIMARY KEY (id);

ALTER TABLE vote ADD CONSTRAINT vote_pkey PRIMARY KEY (id);

ALTER TABLE dish ADD CONSTRAINT fk_dish_menu FOREIGN KEY (menu_id) REFERENCES menu(id);

ALTER TABLE vote ADD CONSTRAINT fk_vote_restaurant FOREIGN KEY (restaurant_id) REFERENCES restaurant(id);

ALTER TABLE users ADD CONSTRAINT fk_user_vote FOREIGN KEY (vote_id) REFERENCES restaurant(id);

ALTER TABLE menu ADD CONSTRAINT fk_menu_restaurant FOREIGN KEY (restaurant_id) REFERENCES restaurant(id);
