Ext.define('Starter.store.Posts', {
	extend: 'Ext.data.Store',
	model: 'Starter.model.Post',
	autoLoad: true,
	pageSize: 25,
	remoteSort: true,
	remoteFilter: true,
	autoSync: true,
    sorters: [ {
        property: 'id',
        direction: 'ASC'
    } ]

});