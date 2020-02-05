ALTER TABLE IF EXISTS ONLY public.address
    DROP CONSTRAINT IF EXISTS address_pkey CASCADE;
ALTER TABLE IF EXISTS ONLY public.cart
    DROP CONSTRAINT IF EXISTS cart_pkey CASCADE;
ALTER TABLE IF EXISTS ONLY public.category
    DROP CONSTRAINT IF EXISTS category_pkey CASCADE;
ALTER TABLE IF EXISTS ONLY public.line_item
    DROP CONSTRAINT IF EXISTS line_item_pkey CASCADE;
ALTER TABLE IF EXISTS ONLY public."order"
    DROP CONSTRAINT IF EXISTS order_pkey CASCADE;
ALTER TABLE IF EXISTS ONLY public.product
    DROP CONSTRAINT IF EXISTS product_pkey CASCADE;
ALTER TABLE IF EXISTS ONLY public.supplier
    DROP CONSTRAINT IF EXISTS supplier_pkey CASCADE;
ALTER TABLE IF EXISTS ONLY public."user"
    DROP CONSTRAINT IF EXISTS user_pkey CASCADE;

ALTER TABLE IF EXISTS ONLY public.cart
    DROP CONSTRAINT IF EXISTS cart_user_id_fkey CASCADE;
ALTER TABLE IF EXISTS ONLY public.line_item
    DROP CONSTRAINT IF EXISTS line_item_cart_id_fkey CASCADE;
ALTER TABLE IF EXISTS ONLY public.line_item
    DROP CONSTRAINT IF EXISTS line_item_product_id_fkey CASCADE;
ALTER TABLE IF EXISTS ONLY public."order"
    DROP CONSTRAINT IF EXISTS order_cart_id_fkey CASCADE;
ALTER TABLE IF EXISTS ONLY public."order"
    DROP CONSTRAINT IF EXISTS order_shipping_address_id_fkey CASCADE;
ALTER TABLE IF EXISTS ONLY public."order"
    DROP CONSTRAINT IF EXISTS order_billing_address_id_fkey CASCADE;
ALTER TABLE IF EXISTS ONLY public."order"
    DROP CONSTRAINT IF EXISTS order_user_id_fkey CASCADE;
ALTER TABLE IF EXISTS ONLY public.product
    DROP CONSTRAINT IF EXISTS product_product_category_id_fkey CASCADE;
ALTER TABLE IF EXISTS ONLY public.product
    DROP CONSTRAINT IF EXISTS product_supplier_id_fkey CASCADE;


DROP TABLE IF EXISTS public.address;
CREATE TABLE public.address
(
    id       SERIAL,
    country  character varying,
    zip_code integer,
    city     character varying,
    street   character varying,
    optional character varying
);

DROP TABLE IF EXISTS public.cart;
CREATE TABLE public.cart
(
    id      SERIAL,
    user_id integer
);

DROP TABLE IF EXISTS public.category;
CREATE TABLE public.category
(
    id          SERIAL,
    name        character varying,
    description character varying,
    department  character varying
);

DROP TABLE IF EXISTS public.line_item;
CREATE TABLE public.line_item
(
    id         SERIAL,
    product_id integer,
    quantity   integer,
    cart_id    bigint
);

DROP TABLE IF EXISTS public."order";
CREATE TABLE public."order"
(
    id                  SERIAL,
    shipping_address_id integer,
    billing_address_id  integer,
    phone_number        character varying,
    cart_id             integer,
    user_id             integer
);

DROP TABLE IF EXISTS public.product;
CREATE TABLE public.product
(
    id                  SERIAL,
    name                character varying,
    description         character varying,
    supplier_id         integer,
    product_category_id integer,
    default_currency    character varying,
    default_price       double precision,
    image_url           character varying
);

DROP TABLE IF EXISTS public.supplier;
CREATE TABLE public.supplier
(
    id          SERIAL,
    name        character varying,
    description character varying
);


DROP TABLE IF EXISTS public."user";
CREATE TABLE public."user"
(
    id       SERIAL,
    name     character varying,
    password character varying,
    email    character varying
);



ALTER TABLE ONLY public.address
    ADD CONSTRAINT address_pkey
        PRIMARY KEY (id);


ALTER TABLE ONLY public.cart
    ADD CONSTRAINT cart_pkey
        PRIMARY KEY (id);


ALTER TABLE ONLY public.category
    ADD CONSTRAINT category_pkey
        PRIMARY KEY (id);


ALTER TABLE ONLY public.line_item
    ADD CONSTRAINT line_item_pkey
        PRIMARY KEY (id);


ALTER TABLE ONLY public."order"
    ADD CONSTRAINT order_pkey
        PRIMARY KEY (id);


ALTER TABLE ONLY public.product
    ADD CONSTRAINT product_pkey
        PRIMARY KEY (id);



ALTER TABLE ONLY public.supplier
    ADD CONSTRAINT supplier_pkey
        PRIMARY KEY (id);



ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_pkey
        PRIMARY KEY (id);



ALTER TABLE ONLY public.cart
    ADD CONSTRAINT "cart_user_id_fkey"
        FOREIGN KEY (user_id)
            REFERENCES public."user" (id)
            ON DELETE CASCADE;


ALTER TABLE ONLY public.line_item
    ADD CONSTRAINT line_item_cart_id_fkey
        FOREIGN KEY (cart_id)
            REFERENCES public.cart (id)
            ON DELETE CASCADE;



ALTER TABLE ONLY public.line_item
    ADD CONSTRAINT line_item_product_id_fkey
        FOREIGN KEY (product_id)
            REFERENCES public.product (id)
            ON DELETE CASCADE;



ALTER TABLE ONLY public."order"
    ADD CONSTRAINT order_cart_id_fkey
        FOREIGN KEY (cart_id)
            REFERENCES public.cart (id)
            ON DELETE CASCADE;



ALTER TABLE ONLY public."order"
    ADD CONSTRAINT order_shipping_address_id_fkey
        FOREIGN KEY (shipping_address_id)
            REFERENCES public.address (id)
            ON DELETE CASCADE;



ALTER TABLE ONLY public."order"
    ADD CONSTRAINT order_billing_address_id_fkey
        FOREIGN KEY (billing_address_id)
            REFERENCES public.address (id)
            ON DELETE CASCADE;


ALTER TABLE ONLY public."order"
    ADD CONSTRAINT order_user_id_fkey
        FOREIGN KEY (user_id)
            REFERENCES public."user" (id)
            ON DELETE CASCADE;


ALTER TABLE ONLY public.product
    ADD CONSTRAINT product_product_category_id_fkey
        FOREIGN KEY (product_category_id)
            REFERENCES public.category (id)
            ON DELETE CASCADE;


ALTER TABLE ONLY public.product
    ADD CONSTRAINT product_supplier_id_fkey
        FOREIGN KEY (supplier_id)
            REFERENCES public.supplier (id)
            ON DELETE CASCADE;

--Sample data--

INSERT INTO public.supplier
    (name, description)
VALUES ('Olivander',
        'British half-blood wizard who was the proprietor of Ollivanders in Diagon Alley during most of the 20th century. Ollivander was widely considered the best wandmaker in the world, and many wizards and witches bought their wands from him.');

INSERT INTO public.supplier
    (name, description)
VALUES ('Weasleys Wizard Wheezes',
        'Look, we love a good joke more than anyone else. What sort of cereal do they serve at Hogwarts? Hufflepuffs! Why did Severus Snape stand in the middle of the road? So you’d never know what side he’s on! See? We know how to have a good time.');

INSERT INTO public.supplier
    (name, description)
VALUES ('Death', 'Bad guy');


INSERT INTO public.category
    (name, description, department)
VALUES ('Fun', 'Such fun', 'Magic');

INSERT INTO public.category
    (name, description, department)
VALUES ('Fight', 'Deadly', 'Magic');

INSERT INTO public.category
    (name, description, department)
VALUES ('Potion', 'Useful', 'Magic');

INSERT INTO public.category
    (name, description, department)
VALUES ('Deathly hallows',
        'The Deathly Hallows are three highly powerful magical objects supposedly created by Death and given to each of three brothers in the Peverell family.',
        'Magic');


INSERT INTO public.product
(name, description, supplier_id, product_category_id, default_currency, default_price, image_url)
VALUES ('Your wand', 'It already chose you', 1, 2, 'GAL', 10, 'product_1.jpg');

INSERT INTO public.product
(name, description, supplier_id, product_category_id, default_currency, default_price, image_url)
VALUES ('Wildfire Whiz-bang',
        'You’ll know these as the huge, showy fireworks that announced Fred and George’s departure from Hogwarts. They are a cacophony of chaos: a series of pyrotechnics that includes a shocking-pink catherine wheel, rockets with long tails of silver stars, sparklers that spell out swear words and an immense, fire-breathing dragon.',
        2, 1, 'GAL', 4, 'product_2.jpg');

INSERT INTO public.product
(name, description, supplier_id, product_category_id, default_currency, default_price, image_url)
VALUES ('Portable Swamp', 'A product which, once activated, floods the area with grim, swampy water.', 2, 1, 'GAL', 6,
        'product_3.jpg');

INSERT INTO public.product
(name, description, supplier_id, product_category_id, default_currency, default_price, image_url)
VALUES ('Love potion',
        'Only last for 24 hours, but the fact that these are allowed in the wizarding world at all is pretty unexpected. Suddenly, the likes of Romilda Vane were spiking Chocolate Cauldrons all over the place at Hogwarts.',
        2, 3, 'GAL', 7, 'product_4.jpg');

INSERT INTO public.product
(name, description, supplier_id, product_category_id, default_currency, default_price, image_url)
VALUES ('Peruvian Instant Darkness Powder', 'Reates impenetrable darkness wherever it’s thrown.', 2, 2, 'GAL', 7,
        'product_5.jpg');

INSERT INTO public.product
(name, description, supplier_id, product_category_id, default_currency, default_price, image_url)
VALUES ('Extendable Ear', 'Weasleys Wizard Wheezes also encourages snooping on other wizards, with Extendable Ears.',
        2, 1, 'GAL', 5, 'product_6.jpg');

INSERT INTO public.product
(name, description, supplier_id, product_category_id, default_currency, default_price, image_url)
VALUES ('The Elder Wand', 'The Elder Wand is one of three objects that make up the fabled Deathly Hallows.', 3, 4,
        'GAL', 100, 'product_7.jpg');

INSERT INTO public.product
(name, description, supplier_id, product_category_id, default_currency, default_price, image_url)
VALUES ('The Resurrection Stone',
        'According to legend, whoever reunited it with the other two Hallows (the Elder Wand and the Cloak of Invisibility) would become the Master of Death.',
        3, 4, 'GAL', 100, 'product_8.jpg');

INSERT INTO public.product
(name, description, supplier_id, product_category_id, default_currency, default_price, image_url)
VALUES ('The Cloak of Invisibility',
        'The Cloak of Invisibility is a magical artefact used to render the wearer invisible, and one of the fabled Deathly Hallows.',
        3, 4, 'GAL', 100, 'product_9.jpg');

INSERT INTO public."user"(name, password, email)VALUES ('admin', 'admin', 'admin@codecoolshop.bp');
INSERT INTO public.cart(user_id)VALUES (1);