Ext.define('Starter.view.Viewport', {
    extend: 'Ext.Viewport',
    id: 'viewport',

    /*
     layout: {
     align: 'stretch',
     type: 'vbox'
     },
     requires: [ 'Starter.view.StorePanel',
     'Starter.view.PollPanel',
     'Starter.view.FormPanel',
     'Starter.view.TreePanel',
     'Starter.view.PostPanel'],
     */
    requires: [ 'Starter.view.TreePanel', 'Starter.view.PostPanel'],
    layout: 'border',

    initComponent: function () {
        this.items = [
            //{
                /*
                xtype: 'container',
                layout: {
                    align: 'stretch',
                    type: 'hbox'
                },
                flex: 1,
                items: [
                */
                    {
                        xtype: 'companytreepanel',
                        flex: 1,
                        margins: 5,
                        region : 'west',
                        collapsible: true,
                        resizable: true
                    },
                    {
                        xtype: 'postpanel',
                        flex: 5,
                        margins: 5,
                        collapsible: false,
                        region : 'center'
                    }

             //  ]
         //   }
        ];

        this.callParent(arguments);
    }

});