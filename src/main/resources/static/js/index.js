// $().ready(function () {
//     show_boards();
// })
//
// function show_boards() {
//     $('#cards-box').empty();
//
//     $.ajax({
//         type: "GET",
//         url: "/boards",
//         data: {},
//         success: function (response) {
//             for (let i = 0; i < response.length; i++) {
//                 let message = response[i];
//                 let number = message['number'];
//                 let title = message['title'];
//                 let author = message['author'];
//                 let content = message['content'];
//                 let createdAt = message['createdAt'];
//                 let modifiedAt = message['modifiedAt'];
//                 addHTML(number, title, author, content, createdAt, modifiedAt);
//             }
//         }
//     });
// }