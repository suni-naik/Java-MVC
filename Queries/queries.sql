INSERT INTO public."User"(
	user_id, name)
	VALUES (1, 'sunil');
	
INSERT INTO public."User"(
	user_id, name)
	VALUES (2, 'rakesh');
	
INSERT INTO public."User"(
	user_id, name)
	VALUES (3, 'vinith');
	
	SELECT * FROM public."User"
ORDER BY user_id ASC 

UPDATE public."User"
	SET name='Rakesh M'
	WHERE user_id=2;
	
DELETE FROM public."User"
	WHERE user_id=3;
	