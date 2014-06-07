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
            },
            {
                name: "updatedTime",
                type: "string"
            },
            {
                name: "product",
                type: "string"
            },
            {
                name: "brand",
                type: "string"
            },
            {
                name: "link",
                type: "string"
            },
            {
                name: "postType",
                type: "string"
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