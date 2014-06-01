Ext.define('Starter.controller.Post', {
	extend: 'Ext.app.Controller',

	stores: [ 'Posts' ],
	models: [ 'Post' ],
	views: [ 'PostPanel' ],

	refs: [ {
		ref: 'postPanel',
		selector: 'postpanel'
	}, {
		ref: 'deleteButton',
		selector: 'postpanel button[action=delete]'
	} ],

	init: function() {
		this.control({
			'postpanel': {
				itemclick: this.onItemClick
			},
			'postpanel button[action=delete]': {
				click: this.deleteUser
			},
			'postpanel button[action=new]': {
				click: this.newUser
			},
			'postpanel #filtertextfield': {
				change: { 
					fn: this.onChange,
					buffer: 350
				}
			}
		});
	},

	onChange: function(field, newValue) {
        /*
		var myStore = this.getUsersStore();
		if (newValue) {
			myStore.clearFilter(true);
			myStore.filter('filter', newValue);
		} else {
			myStore.clearFilter();
		}
		*/
	},
	
	onItemClick: function(button, record) {
		this.getDeleteButton().enable();
	},

	deleteUser: function() {
        /*
		this.getDeleteButton().disable();
		var sm = this.getStorePanel().getSelectionModel();
		this.getUsersStore().remove(sm.getSelection());
		*/
	},

	newUser: function() {
        /*
		var newUser = this.getUserModel().create({
			lastName: 'New',
			firstName: 'Person',
			email: 'new@email.com'
		});

		this.getUsersStore().insert(0, newUser);
		this.getStorePanel().getPlugin('storePanelRowEditing').startEdit(0, 0);
		*/
	}

});