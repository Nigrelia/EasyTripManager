-- View all members
SELECT * FROM members;

-- Or to see the most recently added member
SELECT * FROM members ORDER BY signup_date DESC FETCH FIRST 1 ROW ONLY;
