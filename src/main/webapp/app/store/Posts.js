var DateSorter = function (o1, o2) {
    var v2 = new Date(o1.data.date);
    var v1 = new Date(o2.data.date);
    console.log("hello");
    console.log(v1);
    return v1 > v2 ? 1 : (v1 < v2 ? -1 : 0);

};

Ext.define('Starter.store.Posts', {
    id: 'PostsStore',
    extend: 'Ext.data.Store',
    model: 'Starter.model.Post',
    autoLoad: true,
    pageSize: 200,
    remoteSort: true,
    remoteFilter: true,
    autoSync: true,
    //buffered: true, //this breaks filterby for some reason
    sorters: [DateSorter ]

});


