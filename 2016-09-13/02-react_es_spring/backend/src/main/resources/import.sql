--sequence set
select setVal('hibernate_sequence',1000)
--admin
INSERT INTO users(id, status, display_name, email, password, role) VALUES (0, 'APPROVED', 'Az Nagy Admin', 'nagy@admin.hu', '$2a$10$o5u8Dk27kpto1PMXg4Kine4GZuuqgMuU.IfQA9XS9oCYPdQF7fNvu','ADMIN');
--pending user 1
INSERT INTO users(id, status, display_name, email, password, role) VALUES (1, null, 'Teszt Elek', 'teszt@elek.hu', '$2a$10$o5u8Dk27kpto1PMXg4Kine4GZuuqgMuU.IfQA9XS9oCYPdQF7fNvu','USER');
INSERT INTO public.companies(id, contact_email, contact_name, contact_phone, identifier, name) VALUES (2, 'contact@comp1.hu', 'Kontakt Tibor', '+36401112222', 'comp1', 'Cég 1');
INSERT INTO public.user_companies(company_id, user_id, role)VALUES (2, 1, 'ADMIN');
--pending user 2
INSERT INTO users(id, status, display_name, email, password, role) VALUES (3, null, 'Teszt Mária', 'teszt@maria.hu', '$2a$10$o5u8Dk27kpto1PMXg4Kine4GZuuqgMuU.IfQA9XS9oCYPdQF7fNvu','USER');
INSERT INTO public.companies(id, contact_email, contact_name, contact_phone, identifier, name) VALUES (4, 'contact@comp2.hu', 'Kontakt Eleonóra', '+36403332222', 'comp2', 'Cég 2');
INSERT INTO public.user_companies(company_id, user_id, role)VALUES (4, 3, 'ADMIN');
--approved user 1
INSERT INTO users(id, status, display_name, email, password, role) VALUES (5, 'APPROVED', 'Teszt Nóra', 'teszt@nora.hu', '$2a$10$o5u8Dk27kpto1PMXg4Kine4GZuuqgMuU.IfQA9XS9oCYPdQF7fNvu','USER');
INSERT INTO public.companies(id, contact_email, contact_name, contact_phone, identifier, name) VALUES (6, 'contact@comp3.hu', 'Kontakt Nóra', '+36403232222', 'comp3', 'Cég 3');
INSERT INTO public.user_companies(company_id, user_id, role)VALUES (6, 5, 'ADMIN');
--workers for company 3
INSERT INTO public.workers( id, name, biography, company_id) VALUES (7, 'Dolgozó Anita', 'Igazi szaktekintély', 6);
INSERT INTO public.workers( id, name, biography, company_id) VALUES (8, 'Dolgozó Elemér', 'Igazi szaktekintély', 6);
INSERT INTO public.workers( id, name, biography, company_id) VALUES (9, 'Dolgozó Tamara', 'Igazi szaktekintély', 6);
INSERT INTO public.workers( id, name, biography, company_id) VALUES (10, 'Dolgozó Kálmán', 'Igazi szaktekintély', 6);
--approved user 2
INSERT INTO users(id, status, display_name, email, password, role) VALUES (11, 'APPROVED', 'Teszt Tihamér', 'teszt@tihamer.hu', '$2a$10$o5u8Dk27kpto1PMXg4Kine4GZuuqgMuU.IfQA9XS9oCYPdQF7fNvu','USER');
INSERT INTO public.companies(id, contact_email, contact_name, contact_phone, identifier, name) VALUES (12, 'contact@comp4.hu', 'Kontakt Tihamér', '+36406232222', 'comp4', 'Cég 4');
INSERT INTO public.user_companies(company_id, user_id, role)VALUES (12, 11, 'ADMIN');
--reservations
INSERT INTO public.worker_reservations(id, end_date, start_date, status, booking_company_id, worker_id)VALUES (13, '2016-06-02', '2016-06-01', null, 12, 7);
INSERT INTO public.worker_reservations(id, end_date, start_date, status, booking_company_id, worker_id)VALUES (14, '2016-06-20', '2016-06-03', 'APPROVED', 12, 7);
INSERT INTO public.worker_reservations(id, end_date, start_date, status, booking_company_id, worker_id)VALUES (15, '2016-06-02', '2016-06-01', 'REJECTED', 12, 7);
INSERT INTO public.worker_reservations(id, end_date, start_date, status, booking_company_id, worker_id)VALUES (16, '2016-06-02', '2016-06-01', null, 12, 8);
INSERT INTO public.worker_reservations(id, end_date, start_date, status, booking_company_id, worker_id)VALUES (17, '2016-06-20', '2016-06-03', 'APPROVED', 12, 8);
INSERT INTO public.worker_reservations(id, end_date, start_date, status, booking_company_id, worker_id)VALUES (18, '2016-06-02', '2016-06-01', 'REJECTED', 12, 8);
