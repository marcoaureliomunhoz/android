const restful = require('node-restful')
const mongoose = restful.mongoose

const editoraSchema = new mongoose.Schema({
    nome: { type: String, required: true },
    telefone: { type: String }
})

module.exports = restful.model('Editora', editoraSchema)