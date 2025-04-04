DELIMITER $$

CREATE PROCEDURE InsertRandomOrders(
    IN p_count INT
)
BEGIN
    DECLARE i INT DEFAULT 0;

    WHILE i < p_count DO
        INSERT INTO orders(customer_id, order_number, total_amount, created_at)
        VALUES (
            FLOOR(RAND() * 100) + 1,
            UUID(),
            FLOOR(RAND() * 100000) + 1000,
            NOW()
        );
        SET i = i + 1;
END WHILE;
END $$

DELIMITER ;

-- 사용 예시:
-- CALL InsertRandomOrders(1000);
