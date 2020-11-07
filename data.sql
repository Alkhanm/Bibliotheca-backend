CREATE OR REPLACE FUNCTION CREATE_FIRST_LIST()
    RETURNS
    TRIGGER AS $$
    DECLARE list_total INTEGER;
    DECLARE list_id INTEGER;
BEGIN
    list_id := 1;
    list_total := (SELECT MAX(id) FROM tb_reading_list);
    IF list_total >= 1 THEN
        list_id := list_total + 1;
    END IF;
    INSERT INTO tb_reading_list AS list
    (id, description, name, start_date, user_id)
    VALUES ( list_id,
            'Comece por aqui :) Adiciona nesta lista seus primeiros livros.',
            'Meus livros',
            NOW(),
            NEW.id );
    RETURN NEW;
END;
$$LANGUAGE plpgsql;

CREATE TRIGGER FIRST_LIST
    AFTER
        INSERT ON tb_users FOR EACH ROW
EXECUTE PROCEDURE CREATE_FIRST_LIST();