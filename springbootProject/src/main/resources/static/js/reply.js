// 즉시 실행함수로 만듬 (function(){return{};})()
//키와 메서드를 가진 javascript obect를 return함
//ResteFul방식으로 요청하기


var replyManager = (function() {
	// 댓글 가져오기
	var getAll = function(obj, callback) {
		console.log("get All.....");
		$.getJSON("/app/replies/" + obj, callback);
		/*$.getJSON("/app/replies/" 요청의 주소 obj:board번호*/
	};
	// 댓글 추가
	var add = function(obj, callback) {
		console.log("add.....");
		$.ajax({
			type: "post",
			url: "/app/replies/" + obj.bno,
			data: JSON.stringify(obj),
			dataType: "json",
			contentType: "application/json",
			success: callback
		});
	};
	// 댓글 수정
	var update = function(obj, callback) {
		$.ajax({
			type: "put",
			url: "/app/replies/" + obj.bno,
			data: JSON.stringify(obj),
			dataType: "json",
			contentType: "application/json",
			success:  callback
		});
	};
	// 댓글 삭제 주소창에만 2개를 넣어보자이
	var remove = function(obj, callback ) {
		$.ajax({
			type: "delete",
			url: "/app/replies/" + obj.bno + "/" + obj.rno,
			dataType: "json",
			contentType: "application/json",
			success: callback
		});
	}
	return { getAll: getAll, add: add, date: update, remove: remove };
})();