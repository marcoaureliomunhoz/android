const express = require('express')

module.exports = function(server) {

    // API Routes
    const router = express.Router()
    server.use('/api', router)

    // Services Router - Editora
    const editoraService = require('../api/services/editoraService')
    editoraService.register(router, '/editoras')

}