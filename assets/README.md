# 🎨 AI-MessageCraft Visual Assets

This directory contains all visual assets for the AI-MessageCraft project.

## 📁 Directory Structure

```
assets/
├── README.md           # This file
├── demo-banner.md      # Demo content and examples
├── images/            # Static images and logos
├── screenshots/       # Application screenshots
└── diagrams/          # Exported diagram images
```

## 🎯 Asset Types

### 🖼️ Images (`images/`)
- Project logos
- Technology stack icons
- Banner images
- Social media previews

### 📸 Screenshots (`screenshots/`)
- Swagger UI interface
- API responses
- Application running
- Docker containers

### 📊 Diagrams (`diagrams/`)
- Architecture diagrams (exported from Mermaid)
- Flow charts
- Sequence diagrams
- System overview

## 🔄 Generating Assets

### Mermaid Diagrams
The project uses Mermaid diagrams in documentation. To export as images:

```bash
# Install mermaid CLI
npm install -g @mermaid-js/mermaid-cli

# Export diagrams
mmdc -i docs/ARCHITECTURE.md -o assets/diagrams/architecture.png
```

### Screenshots
To capture application screenshots:

1. Start the application: `mvn spring-boot:run`
2. Open Swagger UI: http://localhost:8080/swagger-ui.html
3. Capture screenshots of the interface
4. Save in `screenshots/` directory

### Badges
Current badges are generated via shields.io:
- ![Java](https://img.shields.io/badge/Java-17-orange?style=flat-square&logo=java)
- ![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.2.0-brightgreen?style=flat-square&logo=spring)
- ![OpenAI](https://img.shields.io/badge/OpenAI-GPT--4-black?style=flat-square&logo=openai)
- ![Docker](https://img.shields.io/badge/Docker-Ready-blue?style=flat-square&logo=docker)

## 📝 Usage in Documentation

### In README.md
```markdown
![Architecture](assets/diagrams/architecture.png)
![Screenshot](assets/screenshots/swagger-ui.png)
```

### In GitHub Social Preview
- Use 1200x630px images for optimal GitHub preview
- Place in `assets/images/social-preview.png` 