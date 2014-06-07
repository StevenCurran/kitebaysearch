Ext.define('Starter.view.TreePanel', {
    extend: 'Ext.tree.Panel',
    alias: 'widget.companytreepanel',

    title: 'FIlter Criteria',
    store: Ext.create('Starter.store.Company'),
    rootVisible: false,
    listeners: {
        itemclick: function (y, n) {
            //Ext.Msg.alert('Navigation Tree Click', 'You clicked: "' + n.data.id + '"');

            var store = Ext.StoreMgr.get('Posts');

            store.load({
                params: {
                    parent: n.data.parentId,
                    node: n.data.id,
                    leaf: n.data.leaf
                },
                callback: function (records, operation, success) {
                    console.log("loaded");
                },
                scope: this
            });

            console.log(store);

        }
    }

});