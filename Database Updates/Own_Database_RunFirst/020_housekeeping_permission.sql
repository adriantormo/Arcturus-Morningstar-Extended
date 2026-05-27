INSERT INTO `permission_definitions` (`permission_key`, `max_value`, `comment`, `rank_7`)
VALUES (
    'acc_housekeeping',
    1,
    'Allows opening and using the in-client housekeeping panel.',
    1
)
ON DUPLICATE KEY UPDATE
    `max_value` = VALUES(`max_value`),
    `comment` = VALUES(`comment`),
    `rank_7` = 1;
