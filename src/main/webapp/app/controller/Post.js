var myForm = new Ext.form.Panel({
    width: 500,
    height: 400,
    // title: record.data.id,
    floating: true,
    //closable: false,
    closeAction: 'hide',
    hidden: true
});


Ext.define('Starter.controller.Post', {
    extend: 'Ext.app.Controller',

    stores: [ 'Posts' ],
    models: [ 'Post' ],
    views: [ 'PostPanel' ],

    refs: [
        {
            ref: 'postPanel',
            selector: 'postpanel'
        },
        {
            ref: 'deleteButton',
            selector: 'postpanel button[action=delete]'
        }
    ],

    init: function () {
        this.control({
            'postpanel': {
                itemclick: this.onItemClick,
                itemmouseenter: this.onMouseEnter

            },
            'postpanel button[action=fblogin]': {
                //click: this.fblogin
                click: function () {
                    FB.getLoginStatus(function (response) {
                        if (response.status === 'connected') {
                            console.log('Logged in.');
                        }
                        else {
                            FB.login();
                        }
                    });
                }
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

    onChange: function (field, newValue) {

        var myStore = this.getPostsStore();
        if (newValue) {
            myStore.clearFilter(true);
            myStore.filterBy(function (rec, id) {
                var message = rec.get("message") || " ";
                return (message.toLowerCase().indexOf(newValue.toLowerCase()) != -1);
            });

        } else {
            myStore.clearFilter();
        }

    },

    onItemClick: function (button, record) {

        /*
         this.getDeleteButton().enable();

         myForm.hide();
         myForm.title = record.data.id;
         myForm.html = '<a href="' + record.data.link + '" target="_new">' + 'Post Data</a>' + '<br/><p>' + record.data.message +'</p>';
         myForm.show();
         */

        var dialog = Ext.create('Ext.window.Window', {
            renderTo: Ext.getBody(),
            title: record.data.id,
            itemId: 'addfiledialog',
            id: 'addfiledialog',
            modal: true,
            resizable: true,
            //width: 500,
            //height: 300,
            width: Ext.getBody().getViewSize().width * 0.8,
            height: Ext.getBody().getViewSize().height * 0.8,
            layout: 'anchor',
            resizable: false,
            items: [
                {
                    xtype: 'label',
                    //text: record.data.message
                    html: '<a href="' + record.data.link + '" target="_new">' + 'Post Data</a>' + '<br/><p>' + record.data.message + '</p>'
                    //forId: 'alert',
                    //width: 420
                }
            ],
            buttons: [
                {
                    text: 'OK',
                    id: 'okbutton',
                    action: 'addfile'
                },
                {
                    text: 'Cancel',
                    id: 'cancelbutton',
                    handler: function () {
                        dialog.close();
                    }

                }
            ]
        });

        dialog.show();

    },

    onMouseEnter: function (theitem, record, item, index, e, eOpts) {
//        console.log(record.data.id);

    },

    deleteUser: function () {
        /*
         this.getDeleteButton().disable();
         var sm = this.getStorePanel().getSelectionModel();
         this.getUsersStore().remove(sm.getSelection());
         */
    },

    newUser: function () {
        //sort the store
        console.log("writing");
        this.getPostsStore().sort();

    }
    /*
     ,


     fblogin: function () {
     /*
     FB.getLoginStatus(function (response) {
     if (response.status === 'connected') {
     console.log('Logged in.');
     }
     else {
     FB.login();
     }
     });

     }
     */
});
