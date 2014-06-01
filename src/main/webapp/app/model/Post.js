Ext.define("Starter.model.Post",
    {
        extend: "Ext.data.Model",
        fields: [
            {
                name: "id",
                type: "string"
            },
            {
                name: "message",
                type: "string",
                convert: null
            }
        ],
        proxy: {
            type: "direct",
            api: {
                read: "facebookConnector.getPostsExt"
            },
            reader: {
                root: "records"
            }
        }

    });