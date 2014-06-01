Ext.define('Starter.view.PostPanel', {
    extend: 'Ext.grid.Panel',
    alias: 'widget.postpanel',

    title: 'Show Facebook Posts',
    store: 'Posts',

    initComponent: function () {
        var me = this;

        Ext.applyIf(me, {
            columns: [
                {
                    xtype: 'gridcolumn',
                    dataIndex: 'id',
                    text: 'Post Id',
                    flex: 1,
                    editor: {
                        xtype: 'textfield',
                        allowBlank: true
                    }
                },
                {
                    xtype: 'gridcolumn',
                    dataIndex: 'message',
                    text: 'Message',
                    flex: 1,
                    editor: {
                        xtype: 'textfield',
                        allowBlank: true
                    }
                }
            ],

            plugins: [ Ext.create('Ext.grid.plugin.RowEditing', {
                pluginId: 'storePanelRowEditing'
            }) ],

            dockedItems: [
                {
                    xtype: 'toolbar',
                    dock: 'top',
                    items: [
                        {
                            text: 'Facebook Login',
                            action: 'fblogin'
                        },
                        {
                            text: 'New',
                            action: 'new'
                        },
                        {
                            text: 'Delete',
                            action: 'delete',
                            disabled: true
                        },
                        '->',
                        {
                            fieldLabel: 'Filter',
                            labelWidth: 40,
                            xtype: 'textfield',
                            itemId: 'filtertextfield'
                        }
                    ]
                },
                {
                    xtype: 'pagingtoolbar',
                    store: me.getStore(),
                    dock: 'bottom',
                    displayInfo: true
                }
            ]

        });

        me.callParent(arguments);
    }

});