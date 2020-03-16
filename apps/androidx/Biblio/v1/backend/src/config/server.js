// Requires
const bodyParser = require('body-parser')
const express = require('express')
const cors = require('./cors') 

// Declarations
const port = 3003
const server = express()

// Pipeline configuration
server.use(bodyParser.urlencoded({ extended: true }))
server.use(bodyParser.json())
server.use(cors)

// Run configuration
server.listen(port, () => {
    console.log(`biblio server disponível na porta ${port}`)
})

// Export
module.exports = server