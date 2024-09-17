# itmd4515-f24-fp-Vgangaswamy
Project Screenshots:

**If the user input passes validation:**
<img width="1134" alt="Screenshot1" src="https://github.com/user-attachments/assets/b9754e63-f021-4211-b912-401fcc8c2c98">
- The above Image is the BEFORE of the form
<img width="1134" alt="Screenshot2" src="https://github.com/user-attachments/assets/325f41b2-b24f-48f3-b6e7-711ae9e8aa8c">
- The above Image is the AFTER of the form, the confiramtion view.

**If the user input does not pass validation**
<img width="1134" alt="Screenshot3" src="https://github.com/user-attachments/assets/54599b7c-2740-4eec-ad75-4152d03de8de">
- The above image displays the fields with wrong input or null input
<img width="1134" alt="Screenshot4" src="https://github.com/user-attachments/assets/5f3a1174-15e3-4104-992f-6f880a6d4b59">
- The above image displays the appropriate error messages to handle them

** Graduate student Requirements: **
- The below screenshot displays the newly created language_id and name of the language in the table.
<img width="907" alt="Screenshot 2024-09-16 at 10 13 30 PM" src="https://github.com/user-attachments/assets/f5e21b20-1603-433f-8473-bede1e391fe5">

*************************************************************************************************************************************************************************************************

-> Redirect and forward operations:
- A redirect sends a response back to the client, asking the browser to make a new request to the target URL. The browser’s URL will change, and it’s typically used when you want to prevent the user from resubmitting a form (Post/Redirect/Get pattern).
- A forward happens on the server-side, where the request is forwarded to another resource (like a JSP) without the client being aware of it. The URL in the browser remains unchanged.

-> Validating user submissions without the Bean Validation API:
- Without Bean Validation API, validation would have to be done manually using imperative logic and manually check each field for constraints like null, length, or format and then manually construct error messages for the user.

-> scale to a real application with 100's of entities:
- If I had to manually validate user input without using a standard validation framework like the Bean Validation API, scaling that approach across hundreds of entities would quickly become overwhelming. Each entity would require its own validation checks scattered across different servlets or layers of business logic. This would not only lead to a lot of repetitive code but also make the application harder to maintain and more prone to errors. On the other hand, using the Bean Validation API allows me to define validation rules directly within the entity classes using annotations like `@NotNull` and `@Size`. This approach makes it much easier to manage and extend the application because the validation logic is centralized and reusable, meaning that adding new entities or modifying existing ones doesn't require duplicating validation code. It’s a cleaner, more efficient way to handle validation in large-scale applications.

-> Why didn't we need to include any additional dependencies (i.e. Bean Validation, JDBC) in this project?
- Payara provides these dependencies as part of the Jakarta EE or Java EE specification.
- Bean Validation and @Resource DataSource.
  
