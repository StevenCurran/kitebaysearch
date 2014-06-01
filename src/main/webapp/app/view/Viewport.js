Ext.define('Starter.view.Viewport', {
    extend: 'Ext.Viewport',
    id: 'viewport',

    layout: {
        align: 'stretch',
        type: 'vbox'
    },
    /*
     requires: [ 'Starter.view.StorePanel',
     'Starter.view.PollPanel',
     'Starter.view.FormPanel',
     'Starter.view.TreePanel',
     'Starter.view.PostPanel'],
     */
    requires: [ 'Starter.view.PostPanel'],


    initComponent: function () {
        this.items = [
            {
                xtype: 'container',
                layout: {
                    align: 'stretch',
                    type: 'hbox'
                },
                flex: 1,
                items: [
                    {
                        xtype: 'postpanel',
                        flex: 1,
                        margins: 5
                    }

                ]
            }
        ];

        this.callParent(arguments);
    }

});