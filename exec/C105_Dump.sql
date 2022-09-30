select *
from perfumes
where idx = 300;

select *
from perfume_accords
where perfume_idx =300;

select *
from accords
where idx = 42;


select *
from accords
where idx in (select accord_idx
from perfume_accords
where perfume_idx = 300);

		
select accord_idx
from perfume_accords
where perfume_idx = 300;

select *
from user_search_logs;


select *
from user_detail_logs;


select *
from wish_lists
where user_idx = 3;


select *
from have_lists
where user_idx = 3;


select *
from users;

select r
from ReviewEntity r
where r.user = userEntity
AND r.idx < lastIdx
orderby r.idx DESC


select *
from reviews
where perfume_idx = 300;

select *
from reviews
where perfume_idx = 440;


select *
from wish_lists
where user_idx = 111;

select *
from have_lists
where user_idx = 111;

select *
from user_accord_class
where user_idx = 111;

select *
from reviews
where user_idx = 109;

select  *
from reviews
where perfume_idx = 500;




select count(*)
from perfumes
where longevity = 2;

select *
from perfumes
where longevity =5

select *
from users
where idx = 111;