function getArticleList(all) {
    var params = {own: all};
    console.log('before ajax')
    $.ajax({
        url: '/articleList',
        method: 'get',
        data: params,
        dataType: 'json',
        success: function(ret) {
            console.log('success')
            parsedData = [];
            console.log(ret);
            // allData = JSON.parse(ret);
            // for(var i=0; i<allData.length; i++){
            //     var single={};
            //     single.title = allData[i].title
            //     single.content = allData[i].content;
            //     if(all) {
            //         single.author = allData[i].authorName;
            //     }
            //     single.createTime = allData[i].created_time;
            //     single.modifiedTime = allData[i].modified_time;
            //     parsedData.push(single)
            // }
            updateArticles(ret);
        },
        error: function(ret){
            console.log('fail')
            console.log(ret)
        }
    });

}

function updateArticles(articleList){
    var i=0;
    articleList.forEach(function(article){
        var ad = $('<div id="article' + i + '" class="articleBox"></div>')
        ad.append('<div class="articleTitle" id="articleTitle' + i +'">' +
            article.title +
            '</div>');
        ad.append('<div id="author"' + i + '>' +
            article.author +
            '</div>')
        ad.append('<div id="create_time"' + i + '>' +
            article.createTime +
            '</div>')
        ad.append('<div id="modify_time"' + i + '>' +
            article.modifiedTime +
            '</div>')
        ad.append('<div class="articleContent" id="articleContent' + i +'">' +
            article.content +
            '</div>');

        i++;
        $('#articleList').append(ad)
    });
}