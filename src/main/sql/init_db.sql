ALTER TABLE IF EXISTS ONLY public."Address"
    DROP CONSTRAINT IF EXISTS "Address_pkey" CASCADE;
ALTER TABLE IF EXISTS ONLY public."Cart"
    DROP CONSTRAINT IF EXISTS "Cart_pkey" CASCADE;
ALTER TABLE IF EXISTS ONLY public."Category"
    DROP CONSTRAINT IF EXISTS "Category_pkey" CASCADE;
ALTER TABLE IF EXISTS ONLY public."Line_item"
    DROP CONSTRAINT IF EXISTS "Line_item_pkey" CASCADE;
ALTER TABLE IF EXISTS ONLY public."Order"
    DROP CONSTRAINT IF EXISTS "Order_pkey" CASCADE;
ALTER TABLE IF EXISTS ONLY public."Product"
    DROP CONSTRAINT IF EXISTS "Product_pkey" CASCADE;
ALTER TABLE IF EXISTS ONLY public."Supplier"
    DROP CONSTRAINT IF EXISTS "Supplier_pkey" CASCADE;
ALTER TABLE IF EXISTS ONLY public."User"
    DROP CONSTRAINT IF EXISTS "User_pkey" CASCADE;

ALTER TABLE IF EXISTS ONLY public."Cart"
    DROP CONSTRAINT IF EXISTS "Cart_user_id_fkey" CASCADE;
ALTER TABLE IF EXISTS ONLY public."Line_item"
    DROP CONSTRAINT IF EXISTS "Line_item_cart_id_fkey" CASCADE;
ALTER TABLE IF EXISTS ONLY public."Line_item"
    DROP CONSTRAINT IF EXISTS "Line_item_product_id_fkey" CASCADE;
ALTER TABLE IF EXISTS ONLY public."Order"
    DROP CONSTRAINT IF EXISTS "Order_cart_id_fkey" CASCADE;
ALTER TABLE IF EXISTS ONLY public."Order"
    DROP CONSTRAINT IF EXISTS "Order_shipping_address_id_fkey" CASCADE;
ALTER TABLE IF EXISTS ONLY public."Order"
    DROP CONSTRAINT IF EXISTS "Order_billing_address_id_fkey" CASCADE;
ALTER TABLE IF EXISTS ONLY public."Order"
    DROP CONSTRAINT IF EXISTS "Order_user_id_fkey" CASCADE;
ALTER TABLE IF EXISTS ONLY public."Product"
    DROP CONSTRAINT IF EXISTS "Product_product_category_id_fkey" CASCADE;
ALTER TABLE IF EXISTS ONLY public."Product"
    DROP CONSTRAINT IF EXISTS "Product_supplier_id_fkey" CASCADE;


DROP TABLE IF EXISTS public."Address";
CREATE TABLE public."Address"
(
    id       SERIAL,
    country  character varying,
    zip_code integer,
    city     character varying,
    street   character varying,
    optional character varying
);

DROP TABLE IF EXISTS public."Cart";
CREATE TABLE public."Cart"
(
    id      SERIAL,
    user_id integer
);

DROP TABLE IF EXISTS public."Category";
CREATE TABLE public."Category"
(
    id          SERIAL,
    name        character varying,
    description character varying,
    department  character varying
);

DROP TABLE IF EXISTS public."Line_item";
CREATE TABLE public."Line_item"
(
    id         SERIAL,
    product_id integer,
    quantity   integer,
    cart_id    bigint
);

DROP TABLE IF EXISTS public."Order";
CREATE TABLE public."Order"
(
    id                  SERIAL,
    shipping_address_id integer,
    billing_address_id  integer,
    phone_number        character varying,
    cart_id             integer,
    user_id             integer
);

DROP TABLE IF EXISTS public."Product";
CREATE TABLE public."Product"
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

DROP TABLE IF EXISTS public."Supplier";
CREATE TABLE public."Supplier"
(
    id          SERIAL,
    name        character varying,
    description character varying
);


DROP TABLE IF EXISTS public."User";
CREATE TABLE public."User"
(
    id       SERIAL,
    name     character varying,
    password character varying,
    email    character varying
);



ALTER TABLE ONLY public."Address"
    ADD CONSTRAINT "Address_pkey"
        PRIMARY KEY (id);


ALTER TABLE ONLY public."Cart"
    ADD CONSTRAINT "Cart_pkey"
        PRIMARY KEY (id);


ALTER TABLE ONLY public."Category"
    ADD CONSTRAINT "Category_pkey"
        PRIMARY KEY (id);


ALTER TABLE ONLY public."Line_item"
    ADD CONSTRAINT "Line_item_pkey"
        PRIMARY KEY (id);


ALTER TABLE ONLY public."Order"
    ADD CONSTRAINT "Order_pkey"
        PRIMARY KEY (id);


ALTER TABLE ONLY public."Product"
    ADD CONSTRAINT "Product_pkey"
        PRIMARY KEY (id);



ALTER TABLE ONLY public."Supplier"
    ADD CONSTRAINT "Supplier_pkey"
        PRIMARY KEY (id);



ALTER TABLE ONLY public."User"
    ADD CONSTRAINT "User_pkey"
        PRIMARY KEY (id);


ALTER TABLE ONLY public."Cart"
    ADD CONSTRAINT "Cart_user_id_fkey"
        FOREIGN KEY (user_id)
            REFERENCES public."User" (id)
            ON DELETE CASCADE;


ALTER TABLE ONLY public."Line_item"
    ADD CONSTRAINT "Line_item_cart_id_fkey"
        FOREIGN KEY (cart_id)
            REFERENCES public."Cart" (id)
            ON DELETE CASCADE;



ALTER TABLE ONLY public."Line_item"
    ADD CONSTRAINT "Line_item_product_id_fkey"
        FOREIGN KEY (product_id)
            REFERENCES public."Product" (id)
            ON DELETE CASCADE;



ALTER TABLE ONLY public."Order"
    ADD CONSTRAINT "Order_cart_id_fkey"
        FOREIGN KEY (cart_id)
            REFERENCES public."Cart" (id)
            ON DELETE CASCADE;



ALTER TABLE ONLY public."Order"
    ADD CONSTRAINT "Order_shipping_address_id_fkey"
        FOREIGN KEY (shipping_address_id)
            REFERENCES public."Address" (id)
            ON DELETE CASCADE;



ALTER TABLE ONLY public."Order"
    ADD CONSTRAINT "Order_billing_address_id_fkey"
        FOREIGN KEY (billing_address_id)
            REFERENCES public."Address" (id)
            ON DELETE CASCADE;


ALTER TABLE ONLY public."Order"
    ADD CONSTRAINT "Order_user_id_fkey"
        FOREIGN KEY (user_id)
            REFERENCES public."User" (id)
            ON DELETE CASCADE;


ALTER TABLE ONLY public."Product"
    ADD CONSTRAINT "Product_product_category_id_fkey"
        FOREIGN KEY (product_category_id)
            REFERENCES public."Category" (id)
            ON DELETE CASCADE;


ALTER TABLE ONLY public."Product"
    ADD CONSTRAINT "Product_supplier_id_fkey"
        FOREIGN KEY (supplier_id)
            REFERENCES public."Supplier" (id)
            ON DELETE CASCADE;

--Sample data--

INSERT INTO public."Supplier"
    (name, description)
VALUES ('Olivander',
        'British half-blood wizard who was the proprietor of Ollivanders in Diagon Alley during most of the 20th century. Ollivander was widely considered the best wandmaker in the world, and many wizards and witches bought their wands from him.');

INSERT INTO public."Supplier"
    (name, description)
VALUES ('Weasleys’ Wizard Wheezes',
        'Look, we love a good joke more than anyone else. What sort of cereal do they serve at Hogwarts? Hufflepuffs! Why did Severus Snape stand in the middle of the road? So you’d never know what side he’s on! See? We know how to have a good time.');

INSERT INTO public."Supplier"
    (name, description)
VALUES ('Death', 'Bad guy');


INSERT INTO public."Category"
    (name, description, department)
VALUES ('Fun', 'Such fun', 'Magic');

INSERT INTO public."Category"
    (name, description, department)
VALUES ('Fight', 'Deadly', 'Magic');

INSERT INTO public."Category"
    (name, description, department)
VALUES ('Potion', 'Useful', 'Magic');

INSERT INTO public."Category"
    (name, description, department)
VALUES ('Deathly hallows',
        'The Deathly Hallows are three highly powerful magical objects supposedly created by Death and given to each of three brothers in the Peverell family.',
        'Magic');


INSERT INTO public."Product"
(name, description, supplier_id, product_category_id, default_currency, default_price, image_url)
VALUES ('Your wand', 'It already chose you', 1, 2, 'GAL', 10, 'yourWand.jpg');

INSERT INTO public."Product"
(name, description, supplier_id, product_category_id, default_currency, default_price, image_url)
VALUES ('Wildfire Whiz-bang',
        'You’ll know these as the huge, showy fireworks that announced Fred and George’s departure from Hogwarts. They are a cacophony of chaos: a series of pyrotechnics that includes a shocking-pink catherine wheel, rockets with long tails of silver stars, sparklers that spell out swear words and an immense, fire-breathing dragon.',
        2, 1, 'GAL', 4, 'wildfire.jpg');

INSERT INTO public."Product"
(name, description, supplier_id, product_category_id, default_currency, default_price, image_url)
VALUES ('Portable Swamp', 'A product which, once activated, floods the area with grim, swampy water.', 2, 1, 'GAL', 6,
        'swamp.jpg');

INSERT INTO public."Product"
(name, description, supplier_id, product_category_id, default_currency, default_price, image_url)
VALUES ('Love potion',
        'Only last for 24 hours, but the fact that these are allowed in the wizarding world at all is pretty unexpected. Suddenly, the likes of Romilda Vane were spiking Chocolate Cauldrons all over the place at Hogwarts.',
        2, 3, 'GAL', 7, 'lovePotion.jpg');

INSERT INTO public."Product"
(name, description, supplier_id, product_category_id, default_currency, default_price, image_url)
VALUES ('Peruvian Instant Darkness Powder', 'Reates impenetrable darkness wherever it’s thrown.', 2, 2, 'GAL', 7,
        'powder.jpg');

INSERT INTO public."Product"
(name, description, supplier_id, product_category_id, default_currency, default_price, image_url)
VALUES ('Extendable Ear', 'Weasleys’ Wizard Wheezes also encourages snooping on other wizards, with Extendable Ears.',
        2, 1, 'GAL', 5, 'ear.jpg');

INSERT INTO public."Product"
(name, description, supplier_id, product_category_id, default_currency, default_price, image_url)
VALUES ('The Elder Wand', 'The Elder Wand is one of three objects that make up the fabled Deathly Hallows.', 3, 4,
        'GAL', 100, 'elder.jpg');

INSERT INTO public."Product"
(name, description, supplier_id, product_category_id, default_currency, default_price, image_url)
VALUES ('The Resurrection Stone',
        'According to legend, whoever reunited it with the other two Hallows (the Elder Wand and the Cloak of Invisibility) would become the Master of Death.',
        3, 4, 'GAL', 100, 'stone.jpg');

INSERT INTO public."Product"
(name, description, supplier_id, product_category_id, default_currency, default_price, image_url)
VALUES ('The Cloak of Invisibility',
        'The Cloak of Invisibility is a magical artefact used to render the wearer invisible, and one of the fabled Deathly Hallows.',
        3, 4, 'GAL', 100, 'cloak.jpg');
