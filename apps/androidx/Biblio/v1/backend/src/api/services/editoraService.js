const Editora = require('../models/editora')

Editora.methods(['get', 'post', 'put', 'delete'])
Editora.updateOptions({new: true, runValidators: true})

module.exports = Editora