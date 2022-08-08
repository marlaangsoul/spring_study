<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>listJson.jsp</title>

<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
    <script>
    const getJson = function (type) {
		
    	//const keyword = document.querySelector('#keyword').value;
    	const keyword = $('#keyword').val();
        const param = {'keyword':keyword};
        console.log(param);
		$.ajax({
			type:'GET',
			url:'/book/listJson',
			dataType:'json',
			data: param,
			success: function(response){
				//console.log(response[0]);
				html = '';
				const book = response[0];
				//console.log(book['title']);
				html += '<tr><th>Title</th><th>Category</th><th>Price</th></tr>\n';
				for(const book of response){
					html += '<tr>';
					html += '<td>' + book['title'] + '</td>';
					html += '<td>' + book['cetegory'] + '</td>';
					html += '<td>' + book['price'] + '</td>';
					html += '</tr>';
				}
				console.log(html);
				$('#book_list').html(html);
			},
			error: function (request, status, error) {
	            console.log(request, status,error);
	        }
		});
		
       
    }

    $(function () {
		// onload
    	getJson();
    });


</script>


</head>
<body>

<h1>책 목록 - Map V</h1><hr>
<p>  
<form>  
<input type="text" placeholder="검색" id="keyword" name="keyword" value="" />  
<input type="submit" value="검색" />  
</form>  
</p>  
<table border="1" id="book_list">

</table>


</body>
</html>