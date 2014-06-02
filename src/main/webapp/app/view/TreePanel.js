Ext.define('Starter.view.TreePanel', {
    extend: 'Ext.tree.Panel',
    alias: 'widget.companytreepanel',

    title: 'FIlter Criteria',
    store: Ext.create('Starter.store.Company'),
    rootVisible: false

});