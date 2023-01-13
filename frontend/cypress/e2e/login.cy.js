describe('template spec', () => {
  it('passes', () => {
    cy.visit('http://localhost:3000')
    cy.get('.nav > :nth-child(4)').click()
    cy.get('#email-login').type('vartand@gmail.com')
    cy.get('#password-login').type('vartan')
    cy.get('.login-button').click()
    cy.get('.links').should('contain', 'Profile')
    cy.get('.links').should('contain', 'Admin')
    cy.get('.links').should('contain', 'Logout')
  })
})