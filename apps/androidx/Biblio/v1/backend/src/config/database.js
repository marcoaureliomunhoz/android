const mongoose = require('mongoose')
mongoose.Promise = global.Promise
module.exports = mongoose.connect(
    'mongodb://localhost/biblio', 
    { 
        useNewUrlParser: true,
        useUnifiedTopology: true
    }
)