# AI-MessageCraft API Examples

This document provides practical examples of using the AI-MessageCraft API for various use cases.

## Authentication

Set your OpenAI API key as an environment variable:
```bash
export OPENAI_API_KEY=your-api-key-here
```

## Message Generation Examples

### 1. Customer Apology Message

**Request:**
```bash
curl -X POST http://localhost:8080/api/v1/messages/generate \
  -H "Content-Type: application/json" \
  -d '{
    "prompt": "Generate a professional apology message for a customer whose order was delayed by 3 days due to shipping issues",
    "maxTokens": 300,
    "temperature": 0.7,
    "messageType": "apology"
  }'
```

**Response:**
```json
{
  "status": "success",
  "message": "Message generated successfully",
  "data": "Dear Valued Customer,\n\nI sincerely apologize for the delay in your recent order. We understand how frustrating it can be when deliveries don't arrive as expected, and I want to personally address this situation.\n\nYour order experienced a 3-day delay due to unexpected shipping complications. While this doesn't excuse the inconvenience caused, I want to assure you that we've taken immediate steps to prevent similar issues in the future.\n\nTo make this right, we're expediting your order at no additional cost and providing a 15% discount on your next purchase. Your satisfaction is our top priority, and we're committed to earning back your trust.\n\nThank you for your patience and continued loyalty.\n\nBest regards,\nCustomer Service Team",
  "error": null,
  "timestamp": "2024-01-01T12:00:00"
}
```

### 2. Welcome Message for New Customers

**Request:**
```bash
curl -X POST http://localhost:8080/api/v1/messages/generate \
  -H "Content-Type: application/json" \
  -d '{
    "prompt": "Create a warm welcome message for new customers signing up for our premium software service",
    "maxTokens": 250,
    "temperature": 0.8,
    "messageType": "welcome"
  }'
```

### 3. Streaming Message Generation

**Request:**
```bash
curl -X POST http://localhost:8080/api/v1/messages/generate/stream \
  -H "Content-Type: application/json" \
  -H "Accept: text/event-stream" \
  -d '{
    "prompt": "Generate a follow-up email for customers who haven't used our service in 30 days",
    "maxTokens": 400,
    "temperature": 0.6,
    "messageType": "follow_up"
  }'
```

**Streaming Response:**
```
data: We hope this message finds you well!

data: It's been a while since we last

data: saw you on our platform, and we wanted

data: to reach out to see how we can better

data: serve your needs...
```

## Text Summarization Examples

### 1. Customer Feedback Summary

**Request:**
```bash
curl -X POST http://localhost:8080/api/v1/summarize \
  -H "Content-Type: application/json" \
  -d '{
    "text": "I have been using your service for about 6 months now and overall I am quite satisfied. The user interface is intuitive and easy to navigate. However, I have encountered several issues with the mobile app. Sometimes it crashes when I try to upload files, and the sync between desktop and mobile versions is not always reliable. The customer support team has been helpful whenever I reached out, responding within 24 hours. The pricing is reasonable compared to competitors, but I would appreciate more storage space in the basic plan. Also, the recent update improved the loading speed significantly. I would recommend this service to others but suggest fixing the mobile app issues.",
    "maxTokens": 100,
    "summaryType": "bullet_points"
  }'
```

**Response:**
```json
{
  "status": "success",
  "message": "Text summarized successfully",
  "data": "• Overall satisfied customer using service for 6 months\n• Positive: Intuitive UI, helpful customer support (24hr response), reasonable pricing, improved loading speed\n• Issues: Mobile app crashes during file uploads, unreliable desktop-mobile sync\n• Suggestions: Fix mobile app issues, increase storage in basic plan\n• Would recommend service despite mobile problems",
  "error": null,
  "timestamp": "2024-01-01T12:05:00"
}
```

### 2. Brief Summary

**Request:**
```bash
curl -X POST http://localhost:8080/api/v1/summarize \
  -H "Content-Type: application/json" \
  -d '{
    "text": "The quarterly sales report shows significant growth across all product categories. Revenue increased by 23% compared to the previous quarter, with software sales leading at 35% growth. The marketing campaign launched in Q2 contributed to a 40% increase in new customer acquisitions. However, customer retention dropped by 8% due to competitive pricing pressures. The development team delivered 15 new features, including the highly requested mobile app optimization. Support ticket volume increased by 12%, but resolution time improved by 20%. Looking ahead, the team plans to focus on customer retention strategies and expanding into new markets.",
    "maxTokens": 80,
    "summaryType": "brief"
  }'
```

### 3. Streaming Summarization

**Request:**
```bash
curl -X POST http://localhost:8080/api/v1/summarize/stream \
  -H "Content-Type: application/json" \
  -H "Accept: text/event-stream" \
  -d '{
    "text": "Long customer feedback or report text...",
    "maxTokens": 150,
    "summaryType": "detailed"
  }'
```

## Utility Endpoints

### Get Message Templates

**Request:**
```bash
curl -X GET http://localhost:8080/api/v1/messages/templates
```

**Response:**
```json
{
  "status": "success",
  "message": "Message templates retrieved successfully",
  "data": {
    "apology": "I sincerely apologize for the inconvenience you've experienced...",
    "follow_up": "Thank you for reaching out to us. We wanted to follow up...",
    "welcome": "Welcome to our service! We're excited to have you...",
    "resolution": "We're pleased to inform you that your issue has been resolved..."
  },
  "error": null,
  "timestamp": "2024-01-01T12:10:00"
}
```

### Get Summary Types

**Request:**
```bash
curl -X GET http://localhost:8080/api/v1/summarize/types
```

**Response:**
```json
{
  "status": "success",
  "message": "Summary types retrieved successfully",
  "data": {
    "brief": "A concise paragraph summary",
    "bullet_points": "Key points in bullet format",
    "detailed": "Comprehensive analysis with details",
    "key_points": "Main highlights and takeaways"
  },
  "error": null,
  "timestamp": "2024-01-01T12:12:00"
}
```

## Error Handling Examples

### Validation Error

**Request with invalid data:**
```bash
curl -X POST http://localhost:8080/api/v1/messages/generate \
  -H "Content-Type: application/json" \
  -d '{
    "prompt": "",
    "maxTokens": -1
  }'
```

**Error Response:**
```json
{
  "status": "error",
  "message": "Invalid input parameters",
  "data": null,
  "error": "Validation failed",
  "timestamp": "2024-01-01T12:15:00"
}
```

### API Key Error

**Response when API key is invalid:**
```json
{
  "status": "error",
  "message": null,
  "data": null,
  "error": "Invalid API key or authentication failed",
  "timestamp": "2024-01-01T12:16:00"
}
```

## Health Check

**Request:**
```bash
curl -X GET http://localhost:8080/actuator/health
```

**Response:**
```json
{
  "status": "UP",
  "components": {
    "diskSpace": {
      "status": "UP",
      "details": {
        "total": 250685575168,
        "free": 150685575168,
        "threshold": 10485760,
        "exists": true
      }
    }
  }
}
```

## Integration Examples

### JavaScript/Node.js

```javascript
const generateMessage = async (prompt, messageType = 'general') => {
  const response = await fetch('http://localhost:8080/api/v1/messages/generate', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify({
      prompt,
      maxTokens: 500,
      temperature: 0.7,
      messageType
    })
  });
  
  const result = await response.json();
  return result.data;
};

// Usage
const message = await generateMessage(
  'Create a thank you message for loyal customers',
  'appreciation'
);
```

### Python

```python
import requests

def generate_message(prompt, message_type='general'):
    url = 'http://localhost:8080/api/v1/messages/generate'
    payload = {
        'prompt': prompt,
        'maxTokens': 500,
        'temperature': 0.7,
        'messageType': message_type
    }
    
    response = requests.post(url, json=payload)
    result = response.json()
    
    if result['status'] == 'success':
        return result['data']
    else:
        raise Exception(f"API Error: {result['error']}")

# Usage
message = generate_message(
    'Write a professional email for a product launch',
    'announcement'
)
```

## Rate Limiting (Production Recommendation)

For production deployments, implement rate limiting:

```yaml
# Example Nginx configuration
location /api/ {
    limit_req zone=api burst=10 nodelay;
    proxy_pass http://ai-messagecraft:8080;
}
``` 