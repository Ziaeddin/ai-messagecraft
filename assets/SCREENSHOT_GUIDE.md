# 📸 Screenshot Guide for AI-MessageCraft

This guide helps you capture professional screenshots of your application for the GitHub repository.

## 🚀 Prerequisites

1. **Start the application:**
   ```bash
   mvn spring-boot:run
   ```

2. **Wait for startup** (look for "Started SummarizeServiceApplication")

3. **Verify it's running:**
   ```bash
   curl http://localhost:8080/actuator/health
   ```

## 📋 Screenshots to Capture

### 1. Swagger UI Interface (`swagger-ui.png`)
- **URL**: http://localhost:8080/swagger-ui.html
- **What to capture**: Full page showing all API endpoints
- **Tips**: 
  - Expand a few endpoint sections
  - Show both Message and Summarize controllers
  - Capture the try-it-out functionality

### 2. API Response Example (`api-response.png`)
- **What to capture**: A successful API response in Swagger UI
- **Steps**:
  1. Click "Try it out" on `/messages/generate`
  2. Use example payload:
     ```json
     {
       "prompt": "Create a professional thank you message for loyal customers",
       "maxTokens": 300,
       "temperature": 0.7,
       "messageType": "appreciation"
     }
     ```
  3. Execute and capture the response

### 3. Health Check (`health-check.png`)
- **URL**: http://localhost:8080/actuator/health
- **What to capture**: JSON response showing service health

### 4. Application Logs (`application-logs.png`)
- **What to capture**: Terminal showing application startup logs
- **Highlight**: Spring Boot banner, port info, Swagger UI URL

## 🎨 Screenshot Best Practices

### Browser Setup
- Use Chrome or Firefox for consistent rendering
- Set browser zoom to 100%
- Hide bookmarks bar for cleaner screenshots
- Use incognito/private mode for clean interface

### Capture Tools
- **macOS**: Cmd + Shift + 4 (select area) or Cmd + Shift + 3 (full screen)
- **Windows**: Windows + Shift + S (Snipping Tool)
- **Linux**: Screenshot tool or `gnome-screenshot`

### File Naming Convention
```
screenshots/
├── swagger-ui-overview.png      # Full Swagger UI interface
├── swagger-ui-endpoint.png      # Expanded endpoint details
├── api-response-success.png     # Successful API response
├── api-response-streaming.png   # Streaming response (if captured)
├── health-check.png             # Health endpoint response
├── application-startup.png      # Terminal with startup logs
└── docker-running.png           # Docker container status
```

## 📝 Adding Screenshots to Documentation

### In README.md
```markdown
## 📸 Screenshots

### Swagger UI Interface
![Swagger UI](assets/screenshots/swagger-ui-overview.png)

### API Response Example
![API Response](assets/screenshots/api-response-success.png)
```

### In GitHub Issues/PRs
```markdown
![Screenshot](assets/screenshots/filename.png)
```

## 🎯 Pro Tips

1. **Consistent sizing**: Aim for 1200px wide for main screenshots
2. **Highlight important areas**: Use annotations if needed
3. **Show realistic data**: Use professional, realistic example content
4. **Multiple views**: Capture both overview and detail shots
5. **Dark/Light modes**: Consider capturing both if your UI supports it

## 🔄 Updating Screenshots

When you update the application:
1. Re-run the application with changes
2. Retake relevant screenshots
3. Update file names if needed
4. Commit new images to git

## 📱 For Social Media

Create a social media preview image (1200x630px):
- Combine app screenshot with project branding
- Add text overlay with key features
- Save as `assets/images/social-preview.png` 